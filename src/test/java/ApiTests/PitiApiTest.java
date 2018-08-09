package ApiTests;

import ResponseMessages.*;
import UserData.*;
import core.ApiTestBase;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import utils.RandomMinMax;
import utils.SingUpParser;
import utils.dbClearUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;
import static utils.PropertiesCache.getProperty;

@Epic("API tests")
public class PitiApiTest extends ApiTestBase {
    private static String token, uid, email, password;

    @Test(dataProvider = "Data collection", dataProviderClass = SingUpParser.class, description = "Sing-In with wrong data", priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    public void SingInSimplePassword(String email, String pass, String confPass,String timeZone,String lang,String validation, String errMessage){
        UserRK faledUserRK = new UserRK(email, pass,confPass, timeZone, lang);
        ErrorRS actualAnswer = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .spec(spec).body(faledUserRK)
                .expect().statusCode(400)
                .when()
                .post(baseURL+"users/sign-up")
                .thenReturn().as(ErrorRS.class);
        assertFalse(actualAnswer.isSuccess());
        switch (validation){
            case "email":
                assertEquals(actualAnswer.getError().getMessage().getEmail(), errMessage);
                break;
            case "password":
                assertEquals(actualAnswer.getError().getMessage().getPassword(), errMessage);
                break;
            case "passwordConfirm":
                assertEquals(actualAnswer.getError().getMessage().getPasswordConfirm(), errMessage);
                break;
        }
    }

    @Test(priority = 2)
    @Description("Sing-In")
    @Severity(SeverityLevel.CRITICAL)
    public void SingIn (){
        UserRK expectedUserRK = new UserRK(getProperty("new.user.email"),getProperty("new.user.password"),getProperty("new.user.password"),getProperty("user.timezone"),getProperty("user.lang"));
        UserRS actualUser = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .spec(spec).body(expectedUserRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/sign-up")
                .thenReturn().as(UserRS.class);

        assertTrue(actualUser.isSuccess());
        token=actualUser.getResult().getAuth_token();
        uid=actualUser.getResult().getUid();
        email=expectedUserRK.getEmail();
        password=expectedUserRK.getPassword();
        dbClearUser.getClean();
    }

    @Test//(priority = 3)
    @Description("Sing-Up")
    //@Severity(SeverityLevel.CRITICAL)
    public void SingUp(){
        UserSingUp expectedUser = new UserSingUp(getProperty("user.email"),getProperty("user.password"));
        UserRS actualUser = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .spec(spec).body(expectedUser)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/sign-in")
                .thenReturn().as(UserRS.class);
        assertTrue(actualUser.isSuccess());
        //assertEquals(getProperty("user.id"), actualUser.getResult().getUid());
        token=actualUser.getResult().getAuth_token();
    }

    @Test(dependsOnMethods = "SingUp", priority = 4)
    @Description("Invite sending")
    public void Invite(){
        InviteRK inviteRK = new InviteRK(getProperty("user.gmail"),"Invite messages", "1");
        InviteRS inviteRS = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization", "Bearer "+token)
                .spec(spec).body(inviteRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/invite")
                .thenReturn().as(InviteRS.class);
        assertTrue(inviteRS.isSuccess());
        assertEquals(inviteRS.getResult().getEmail(),getProperty("user.gmail"));
        dbClearUser.getClean();
    }

    @Test (dependsOnMethods = "SingUp", priority = 5)
    @Description("Restore PASSWORD")
    @Severity(SeverityLevel.CRITICAL)
    public void PassRestore(){
        UserRK userRK = new UserRK(getProperty("user.email"),getProperty("user.password"),getProperty("user.password"),getProperty("user.timezone"),getProperty("user.lang"));
        RestoreRS actualAnswer = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .spec(spec).body(userRK)
                .expect().statusCode(200)
                .when()
                .post(baseURL+"users/restore-password")
                .thenReturn().as(RestoreRS.class);
        assertTrue(actualAnswer.isResult());
        assertTrue(actualAnswer.isSuccess());
    }

    @Test(dependsOnMethods = "SingUp", priority = 6)
    public void userPrifileAvatar(){
        UserSettingsAvatarRK userSettingsRK = new UserSettingsAvatarRK();
        userSettingsRK.setEmail(getProperty("user.email"));
        String userName = "Test User"+ new SimpleDateFormat("_dd-MM-yyyy_HH:mm").format(Calendar.getInstance().getTime());
        userSettingsRK.setName(userName);
        String timeZone = Integer.toString(RandomMinMax.Go(1,17));
        userSettingsRK.setTime_zone(timeZone);
        String lang = Integer.toString(RandomMinMax.Go(1,3));
        userSettingsRK.setLang(lang);
        String phone = "38095"+Integer.toString(RandomMinMax.Go(1000001,9999999));
        userSettingsRK.setPhone(phone);
        userSettingsRK.setAvatar("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAQAAAAEACAYAAABccqhmAAAdP0lEQVR42u2dbYwd1XnHz9y774C9FsZgU8gaFKoYURycqq3spEvt9EOgipMQtYqCukmVqPlQ7CgSlA8VaykqIqoSgyqlImqygKp+gBZHSVGiQlkbu+oL69iiuAULe41jLza2/IbX+749z9yZ3dnZeZ/zOvP/SZe7u3fZO3c85z/P+T/PeY7DgLV88LnP9PKnjd63/d5zn/cg6PV7S77NYf646H096j2IYe/50C2v7LuY708CU3B0HwBIhw90GuR9rDXY6UED+/d1H1eIvawlFIe8xygXhkO6DwokAwEwDG+w97PFwV72Dq4biiB8URiGKJgFBEAzfMD3s9aAp4dpd3VZULQwzFqCMKz7YOoMBEAxgTv8dlafAZ8GCcIehghBORAABfBBT4O9n7UG/cd0H4/hnGCLYrBH98FUHQiAJLxB7z9W6j4eS7nEWmKwB2IgBwiAQLzwfifDoJeBLwa7MU0QBwSgJF4ufoC1Bj7CezXQNGE3fwyhBqEcEICCeO79AH/8qe5jqTnPsZYQDOs+EBuBAOSED/wB/jTIcLc3DYoKBrkQDOk+EJuAAGTAC/N3eg/M7c2GvAKaHuzG9CAdCEACfOD3sdagH2AY+LZBQjDEWkIwqvtgTAUCEIE38AcZ5vdVgXyCQQjBciAAAQKh/hO6jwVIYRfD1GAJEACGOX7NgEcQoPYCAFe/tiBrwGosAF7VHt0JsCCn3tBCpJ11rS6snQB44f4gf+zQfSzAKJ5mrYigVtOCWgmAt0BniGGeD6Ihf2CgTguPaiEAXlpviCHcB9mgacFAHdKGlRcAPvjJ2R9kuOuDfFA0QFOC3boPRCaVFQBvrk+hHO76oAwUDWyvqjdQSQHAXB8IprLeQKUEAA4/kEzlMgWVEQAvrz/E7G+jDcyG2pwPVKVuoBICgJAfKKYyUwLrBYAPfnJpEfIDHTzNRWCn7oMog7UCAJcfGILVWQIrBcCb79PgxwIeYALW+gLWCYDXjJMGP+b7wCTIF9huW3NSqwTAW7r7E93HAUACX7NpibE1AsAH/yBDpx5gB7u4CAzqPogsWCEAfPAPMfTnA3bxHBeBAd0HkYbxAoDBDyzGeBEwWgAw+EEFMFoEjBQAL8c/zFDWC6oBpQn7TawVME4AMPhBRTFSBEwUACqmwOAHVeQwF4CNug8iiFECgDk/qAFGeQLGCAAGP6gRxoiAEQKAwQ9qiBEioF0AUOEHaoz2ikGtAoDafgD0rh3QJgDeqr7Xdb0/AAZxv65VhFoEwFvPTx8YS3oBaC0l7tfRT0C5AKDQB4BItBQK6RCAYYY2XgBEsZcLQL/KN1QqAGjgCUAqShuNKhMAr3X3y6reDwCL+YKqluNKBACmHwC5UGYKShcAmH4AFEKJKahCADDvB6AY0v0AqQKAeT8ApZHqB0gTAC/0H2WY9wNQBvID+mRNBWQKwDBDvh8AEUirD5AiAHzw07zlBxJPCAB149tcBHaL/qPCBYAP/j7+ROkLhP4AiIOmAhu5CIyK/KMyBGCYIfQHQAbCpwJCBQCuPwDSEZoVECYAcP0BUILQrIBIAUDBDwBqEFYgJEQAvFr/X2k9JQDUi0+KWCsgSgCGGYw/AFQixBAsLQBo7AmANko3FC0lAJ7xR2HIx3SfCQBqyAnWqg0obAiWFYBBhp7+AOik1N4ChQUAaT8AjKBUWrCMAAwy3P0BMIHCUUAhAfDq/Y/r/tQAgAXWF1knUFQAhhg28wTAJAptNppbAHD3l4tz+1Wxf/BSO5u/1KH7YwE15I4CigjAEMPdvxTOzRN8oH/EnJXTzFlzjbGVU+7XqpjnosA8UZg/283YRMMVCfdxtot/39R9ikAxcpcI5xIAOP/FcGiA33XZvbs36A7fOav7kJKZbLL5M11s/v3rXIGY488QBSvInRHIKwCDDM5/Zhr3XHAHfuPjl3UfSmkoMph7axUXhetdcQDGkisjkFcASFlw90+BBn5jyxmlYb1KfDGgByID47jEBaA36y9nFgDU/KdDIX5z22k+r5/QfShq4FOFuXdXsLn9a2A0mkXmNQJ5BGCUoeY/lua2Mdb41Dndh6ENEoHZN1cjIjCDE1wA+rL8YiYB4IO/nz+9rvtTGUnXLGv74gnx6TtFzE402PhYB+tcNcM6emfK/TEeEcz+y2+4UQHQzv1cBIbTfimrAAwxpP6WQ4P/K8cSQ/45PsAuH+ti1/ggGz/d6Q44Udz24HnWvXYq8rWTP7/Rfc8g46c7Mr//DXdMsA4uCj1rJ1nv3eO5xIG8gdnX1iIa0EumwqBUAfBSfxd0fxrjSBn8c5fa2dhrq9gH/32dtEO465tj7Ib10e//7o/WsivHxLn1PVxobtx0hT8+Ys2uudTfn/ygk11+/nZ23Uwna28o24UeLGVVWkowiwBgk48wKYN/fN9q9s4vbmBz83IPQ6UA+NDgJxFYt+1CqhBQtPHus2tZ5/kedls3UocaSN1MJIsAjDKYf0tofulEdG6fz4HP/uM6dvKdNiXHoUMAFs4BH/wkAms2J9c4+CLQdq7bFYGmg2hAIalmYOK/Bpp9Lqfx2+dYc+vY8hf44H//79exD0+qGfyETgHwIa+g78sfJnoEvgjMne1id17XDRFQS2Lz0DQBGGIw/xagGv62rx2NfO3UP6+ROt+PwgQBICga+E1+LHGGJOGLwMQHHVwEelh3U5wZChJJNAPTBACVfwHceX9Euu/Df1/B3v/ZjcqPxxQB8KFI4Mb7Pop9fepiGzvy9K1sfrLhTgdWtquLlmpMYmVgrABgm6+lUHlv84FfL/u5f1GLTO9lxTQBINJEgFKTR5651f36tu5OtqqjXfkx1pDY7cSSBGCIIfxfoO1b/xdZ2z/60k3s/Mj1Wo7JRAEg0kTg/MHr2eiLN7lfr+poQ4ZAPrHTgCQBQPjvkXT3f+up27Qdl6kCQGx45FSiJ/DeCzezi0d63K9XtDeRIZBL7DQg8owj/F+KiXd/wmQBIGNww45TsdkBmjLRVGDqQssH6Go22PqebhQNySNyGhAnANjo0yPu7k8X8KFdessjTBYAgqoHP8EjgTiuHO9yMwM+NPaRIZBGZLegOAEYZSj+cYlz/s8eWOHW2+vEdAEgqFCI1izEQeeQzqUPicCtXTAHJRBZFLRMAFD8swi18mr71juRr/0vD1/Hx/SugbdBANKOkyIp8lHCWZR13Z1sNURANMuKgqIEALX/HnFr/HWbfz62CACtKiRTMG7tAJmBZAqGQYZAOMvWBkQJwDDDVt8uceZfMI2lE1sEgKB1A2u3xi9Mizve69qarK8HGQJBLNtSPEoAJK9hs4Okst9gCksnNgkAcc9jJ2OzAsECoTDIEIiDC8CSk7jkG3T+WSR20Q+H3H8dlX9hbBMAWjh01zfGYl9PSqsiQyCMJZ2CwgIwyND22yXO/U+6U6nGNgEg7nz4DOvdMB75Wpq3ggyBEJa0DQ8LwDDD/N+l/S/fivx5nGGlAxsFgAzBex49Gft6luIqZAhKscQHCAsA5v+s1d6bIoAoxl7rZadfXaX7EF1sFAAiaa1AXFowDDIExQn6AAtfIP+/SHPLGdbYcjbyNd3lv0FsFYC0tGBWkSVzEA1GCrFQDxAUAOT/PeLm/4RJA8tWASCS0oJZowCCRIAiAZiDuVioBwgKwBDD8l+X9m8fid3A06SBZbMA0N2f0oJlowACGYLcLCwPDgoAhQT36j4y3SSV/xIjj6/XfYgL2CwAhKgowAcNRjJzmAsATfmXCAAMQJZsABIQAHGIjAJ81nR2sFu6sE9hGr4R6P4HBuAiSQYgAQEQi+gogECGIBOuEegLABqAeCRt8hlev66bKgiAjCiAQIYgFbdBiC8AgwwVgC5JGQAIgBzSooCijVeQIUjErQj0BYBaBX1e9xGZQNvXj8Zu+QUBkENaFFCm9oIyBH093ez6NmxUGuKnXAC2+wIwzFAC7BJXAkyYVAZMVEUAiKQoQET/BWQIluGWBPsCgAyAR5IAmFQGTFRJANKaiIpYgr26s52t6+rU/VGNgTIBDrb/XiQtBQgBkAvtOtz30IeRr4mKvihDQCIAc9BllYMeAItAAPST1DTkre/dttBGvAzIECxwPwQgAARAP0ldhMMdhMtA3YXIHKx5hsAVgEGGFKBLUhcgAgIgn6R+AaKbsSBDwHZBAAKkVQGa0gvQp4oCQKiYBgSpcYbAFYAhhlWALmkCYNqgqqoAJDUMkdWPoaYZgucc1AAsAgEwAxXZgChqmCHYCwEIAAEwg6Q9BWVvylKzDIErAOgD4AEBMIdNTx6PfU32iswaNRg57FShCpCaeLCIHXzyQlmAxscvx75u2qCqsgAkfTZKB16TvC8jBQBrOjrcnYkimWyy+TOt8zs9N++Kho1Rg7UCQIOe7taNuy7Htu8SjWmDqsoCkGQEGgMXgbl3V7DJf72FHT0/aWVdgZUCQIO++cCvlQ18H9MGVZUFIG0vQaPgQjD60mp24e0e6zYusU4Akvbsk40JW4IHqbIApG0jZiL+9WFTStE6AYjbsVcFNKhMgkpmu9dORb6mYp4sE6oIjEsFmkowQ2FLNsEqAWjcc6EV+gNgKMFCJRuyCVYJAA1+EgEATOX8wevZ6Is3LfmZyXsZWiUASf36ADCBuLZxplYZWiUASTv2AGACSa3MyRdY39PtLkU2BWsEAPN/YAtJC5Zo7FOn4pXtYlc0FsUaAWh+6URilR4AppBlvYIpOxhZIQBp+/UBYBpZ6jBWtDfdaECnL2CFAKR16gHANLIuW9bdmswKAdBZ/ANAUbJ2LyJfQFcJsfECoLP0F4AyUANTqsjMio4SYuMFAMU/wFaK7GtIy4/7etT5AsY3BEHuH9hMkR6GCkuIDxvdEgy5f2A7ZVqZK+hWbHZPQOT+QRUoszRbcgnxXmPbgiP3D6pC1AKhPEgsIX7O2I1BkPsHVaLshiaSdjEyd2cg5P5BlRC1rZzgEuJdRm4Oakvun5Z++rR1zcV25wFiIWNtJrDajvYRaPLzbzIi9zMQWEJs5u7AzW1jrPGpc7oPYwl00VF555Vj3YmGDrWyoguy9+6rrHfDuPEXpulQLj143pPCaOojSCK8etMVI8VY5LZm5AuQCJRMFboC0Mu/MKrSxpTcP1189A925sDKwvM3EoE1Wy7FNu8E0VB0dX7khsIDhoSYhIC2GzdFhOOahRRFQAnxKjeGMKka0G35/cUTug/DLeOkOVtUY4ci0N0pqYknaEGhMjnmojoa0+C/mQuwKS3GZXSWLlpCfMsr+xxfAIaZIbUAunP/dAG+9/zN0tp/W9XvXjGijLIoaFpGm43oFuCyKcE4CpQQ7+UC0O8LwB7+9HmtZ4bommXtO49oe3uaa9I/jqi7fhx0MVJPf1NCU93Q+aalsyr2MTBhxyFaHyDjGstZQvxTLgDbfQEYZAakAnXm/oso84X51oNYx89kVw5T1pQ7km5oINC8OE/ENcHP+emC553QLQK0QpCmmLLIWEK8iwvAoC8A2/nTy9rOiEfb148yZ416syzL4KeL7m1+wz4y67gX38UY12QtP6N3NOgxzzak1GyQUbXhkVO1jQSyDv4js3TuHTbGT9NYwnlfy8/73RnOO6FTBGRvcU5QCTFlCRL4AheAPb4AbORPv9JyNjx05f7TnFm6w78647CDBZISvfzsbuIX45bmfOxdqs7TgaQaeRLc/VxsD8zwr3P+XfqLdN43t817Nnc0dz58xs3S6ICmPDTllElKCfEnuQAcWnhFdyZAR+4/qYXzhDfwDwjIRpIQ/FFb/J2JUlWUIagTSYafe94LDPwwJASb2xjb1hZ9aZPobthxinX0zij//KJTgnHElRBTBoCegwKgtS+Ajtx/nApTqPn8tBMb5hflvmZLCKKigaSNPqtG3BJZEl0678cFB0M0PfhmR/R517kJadn1AXkI7U50mAsARf1LBGCIaVoVqCP3H6fAI1yDfs4vQllDMe5iJD/gnkdPKj0HuogK/Ul0n52Sd97p3ei8r40wyHX5AbJSgnEESoif4wIwQD8LCsBO/vQD5WeB6cn9R12EZDa9MC2/FVOcCOh2p1UQJbyyB79PnAjoEt+kKai0c9BssNu7u77T98v936fvgwKgxwjUkPvXeRH6rG+0LsYgdYgCwsJLYT+d9zFFDhT5MTsixFeXIShyfUBWuAj87mf2vfmf9PWS06DDCNSR+4866c9MqrsIfbZGGFSUFqxqbUBU+usFPviPKE6AbODi+3BIfGnwkwiYcE5k84cHDi6M+7AADDPFJcHNLWdYY8tZpScgXIlFrvNr6o1gl0c7l6aqqpwRCLfJPsYH/o+m9OyK83D78qzMxidOaEnHjjy+Xtl7NRz2X9v2H/wd//uwAAwyxRWBqht/hsN/CkGfmlQX+oehzMCX2xfvRlQX8AkeBVSRcPhP5110piUrNBV4rHPpm+uYBqhKB/o0Hef7W/ePfMf/PiwA/Ux1bwDFHkA4/6zz7u8TjgJ03YlkE7zTqTJck3iIC++mQBSgY6GWzAVQUbQ3nAfuf2PkFf/7Zf8COnwAlZt/hOf/Ou9CPpv5RfhgIAqoYk1AOPevY+4fJuwFqK4J0JEFCM7/iSgBGGaqlwbzKIDKgFX0AAyGoTrnoEHC4ajOElVZBENdmnbtmtR/3oknOhczAqoFQHUGIDz/J6IEQE89AInAV45JXwwUFAATwn+f4DSgij0DggJgQvjvEzYDNz15XMn76kj/8fD/r3j4/93gz6IEQOvCIMoKOHw6ICsaCAoA5Z9Fl50WJXghVlEAgttlmyS84VSsTAHw+xvSnF9VCXCQtobze3/wxsh/BH8WKcNcBEb5U75dDUXDI4JzKy+zKzNi1weMn+5YmHPpyP3HEbwQqSCoc5UhI0QQs9caC8t+X5wutrpSBlE+gOzPrwM+0E9/9sDBWyN+vhwuALv50w5tRxvgwtQ0O3ltUsrffnzCjDCU2Jqwaq1qmBR5RVVkVpGm4/x46/6RPwv/PE4AjGgQ4nNtdo69d3WczQn+d4IA6AECoB4+//8qn///Q/jnsSOAiwBNQlfqPnCfaT76j49fYxOz4q4cCIAeIABq4Vf5Rzz8vyHmtWhM3DR0dn6enZ6Y5NMCMfNjUz2AqmNCDYBPHQSAh///xMP/h6JeSxIAo6YBQc5NTbPTAnwBk+5EUbXpVcXkLEAViQv/icQY2LRpQJCPZmbZKJ8SlPEFTLoQH4lpVlFFTKoDCJcDV42k8N97PR4TpwFByvoCplyIUQtTqoxJlYDhdRhVIyn8J9IEQHu34DTK+gK7JvStBPQJrwisAyb4L9SZ6ZGKC29U8U+QVO0zoigoA0V9AROKUuoU/vtQ78WXDFsNWDXiin9Cv5OMzl6BeSniC1Df/+9pDEfr4ELHoTP6omLwxzrnc+8qZBNRtf9hsgiAcduHJ1HEF9AZBXyDD/47anb399EZBTzYNu/uGVBlepqN1Vv2vZnYXirT2TfdDAyT1xfQ1RUo3AegjuhIxdZh7p9m/vlkFYB+prpTkADy+AKqewMkbVZRJ2gK9oxC8aXQ/5GKO/9EuPNPHJlPgy1mYJg8voCqkDRpk4o6Qi3Zn1EkvnWYcmUx/wK/mw0uAAP86Se6P1wR8vgCskUAgz8a2TsyEVV3/X343f8v+N3/b7P8bq4r3eTKwDTy+AKyLkYK+2ntedXDz6LI2pylTqKbVvkX8fvZ0dE2XDRZfQGam74ocKPKOtSci2DCO++iFgtRww8qsqqL1xJu+51GXgGglOAoszQK8MnrC9CagaKdg6nKb1sb7vp5IVOWzntRAab6CjrvVZ/vB6G7f3ez0ZeW+gv9P/kwqVtQGfLWC1B4OjLrsLf5c5oY0MV3d6O1ug8DvxwUiR3gQkCCkFY6TFMsGvCbayq4cV1/kigiAH38SU3rVMkUXUdAYerpiIuxmz/qMM/UybEYva7TnT4Ofve/69P73jya5/8ppJO2FQalIaq/AAC6yFr4E6aoAPSxikQBPiL6CwCgA5r7dzUb9+W9+3v/bzGqkBEII6PvIACyyev8BykjAJXICIQR3XcQAJkUcf5D/39xqhgF+MAXADZQ5u5PlBUAigIOMQvXCGQBvgAwGar553f/3yp69/f+RjlsXiOQBdqU5OS1CfgCwDjy1PzHIaRcQsuW4gohX4BE4PK0IRvagdoTtdV3EUQJgPHNQ4V8zokpdnZySvdhAJDa7DMrwgomq1IinMal6Rk3GoAvAHRRpOQ3DpECUMm0YBTwBYAuyqb9Iv6eOEzeTkw08AWADpK2+SqC8DVTVTcEl31e+AJAEaKMvyAyBKCPtWoDKj8V8IEvAGRTpt4/5e+Kx6bNREQBXwDIJMsmH0WQ1jahblMBAr4AkIGM0N9HpgDUJiuw7LPDFwCCEO36R/x9edQpKxAGvgAQgWjXP4z0zml1KRCKAr4AKIPIgp84VAgATQWG+eNe2e9lIvAFQBH4wHyHh/6bZYX+gfeRj7dWYJjV0A9YOAfwBUBGaN7fbDifFVHrn+G91FBnP8AHvgDIgux5fxCl3dPr7Af4wBcASaiY9wdRvn1CHesDwsAXAFHIzPfHoUMAam0KBoEvAHxUmX4R76semIKLwBcAKk2/iPfWAxeBfv70uq73Nwn4AvWmveE8cP8bI6/oeG+tWyhWvaFoHuAL1BMRjT3LoH0P1SrvLVAE+AL1oWxPfxFoFwCiapuNlgW+QPUpupmnaIwQAAIisBTyBWhTkmmoQOUwZfATxggAARFYCvkCo+MT7OoMfIGqYNLgJ4wSAIKLALUTq32NQBDarPTc5LTuwwAl0VHok4aJAoBCoQguTE2zU1wIMCOwE12FPhmOyzwgAtHAF7ATUwe/d2zmAk9gOfAF7MK0OX8YowWAgAhEA1/AfEwf/ITxAkBABKKBL2AuNgx+wgoBIFAxGA18AfMwocIvK9YIAIG1A9HAFzAH3bX9ebFKAAhvFeEehqXEy4AvoA9a0tvWcP5Y16q+EsdtH14/gSGGNOEy4Auoh9J8zYYzoGM9v4BjtxOvVoAigVq3F4sCvoA6qLqvq9H4nIk5/ixYKwA+aDQaDXwB+ahu4CkD6wWA8FqODzH4AsuALyAeb77/56pad0v+LNUAvkA88AXEYfN8P+bzVAfPFxhkmBIsA75AeSjk72w4j9o634+iUgLggylBNPAFilGlkD/is1UTZAnigS+QnYbDXu1qNP6kSnf9IJUVAB8uBDtZa1qAaCAAfIFkvLv+U/yu/13dxyL5c1YfLgJ9rDUlQDQQAL5ANJTb72w0vvrpfW8e1X0ssqmFAPjAG1gOfIFFqjzXT/jM9QKZgmjq7gtU0eHPQu0EwMerG6AqQkwLPOroC1C433CcHVXJ6+eltgLg4y0xHuSPj+k+FhMgX+C9q+OVFwF+4Z/m4f6TNi3dlXQegDct2Ok9au8PkC/w3tVrldyslOb5/I7/LA/3/7pu4X7M+QA+ASFA5yEObU92YWpG92EIAQM/GghABF7acJChD6HrC5y8Nqn7MEpB/fk6Gs7jdUjr5QUCkIAnBBQRDLAaTw1s9AX8Oz4f+H+HgR8PBCAD8Ajs8QUQ6ucDApCTumcNTPUF4OoXAwJQEK856QCroU9gki9A8/uGw35sWzNOU4AAlMSbHgyw1vSgNlGBTl/Au9v/sN1xfogwvxwQAIF41YUkBLTmoPJegUpfwJvb/9Jx2N/UtWpPBhAASXgLj/xHpcVAli/gD3oe4r9cpwU6KoEAKMATg37WEoNKThNE+QIU3vNB/ws+6P8Ng14+EADFeNOEftYSg0otRCrqC9CCnKbj/Iz/b68ivFcLBEAzXjbBf1gvCFl8ARrwDnP28+fX4N7rBQJgGIEIYaP3sLLNue8LUBttHtL/Dx/sh3CHNw8IgAV4otDHFkWBUo+mRQt7+eMifxzyHqO3vLLvkO6DAslAACzGq0HY6H3b7z33eQ+CXi8bQRxmrYFNjHoPYth7PsQH+sV8fxKYwv8Dhy6Ksh7unoQAAAAASUVORK5CYII=");

        UserSettingsRS response = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization", "Bearer "+token)
                .spec(spec).body(userSettingsRK)
                .expect().statusCode(200)
                .when()
                .post("https://a1.chis.kiev.ua/api/web/v1/"+"users/settings")
                .thenReturn().as(UserSettingsRS.class);

        assertEquals("true", response.getSuccess());
        assertNotEquals("null", response.getResult().getAvatar());
        assertEquals(timeZone, response.getResult().getTime_zone());
        assertEquals(userName, response.getResult().getName());
        assertEquals(phone, response.getResult().getPhone());
        assertEquals(lang, response.getResult().getLang());
        assertEquals("0", response.getResult().getIcon());
    }

    @Test(dependsOnMethods = "SingUp", priority = 7)
    public void userProfileIcon(){
        UserSettingsIconRK userSettingsRK = new UserSettingsIconRK();
        userSettingsRK.setEmail(getProperty("user.email"));
        String userName = "Test User"+ new SimpleDateFormat("_dd-MM-yyyy_HH:mm").format(Calendar.getInstance().getTime());
        userSettingsRK.setName(userName);
        String timeZone = Integer.toString(RandomMinMax.Go(1,17));
        userSettingsRK.setTime_zone(timeZone);
        String phone = "38095"+Integer.toString(RandomMinMax.Go(1000001,9999999));
        userSettingsRK.setPhone(phone);
        String lang = Integer.toString(RandomMinMax.Go(1,3));
        userSettingsRK.setLang(lang);
        String icon = Integer.toString(RandomMinMax.Go(1,6));
        userSettingsRK.setIcon(icon);

        UserSettingsRS response = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .header("Authorization", "Bearer "+token)
                .spec(spec).body(userSettingsRK)
                .expect().statusCode(200)
                .when()
                .post("https://a1.chis.kiev.ua/api/web/v1/"+"users/settings")
                .thenReturn().as(UserSettingsRS.class);
        assertEquals("true", response.getSuccess());
        assertEquals(icon,response.getResult().getIcon());
        assertEquals(timeZone, response.getResult().getTime_zone());
        assertEquals(userName, response.getResult().getName());
        assertEquals(phone, response.getResult().getPhone());
        assertEquals(lang, response.getResult().getLang());
    }
}
