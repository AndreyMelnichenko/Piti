package Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AccountSettings {
    //---------Main Area
    public SelenideElement mainArea(){return $(By.xpath("//div[@class='content ps']"));}
    //---------Create User Button
    public SelenideElement createNewUserButton(){return $(By.xpath("(//div[@class='bottom'])/div[2]"));}
    //---------Add new User Pop-up
    public SelenideElement emailNewUser(){return $(By.xpath("(//input[@name='email'])[2]"));}
    public SelenideElement nameNewUser(){return $(By.xpath("(//input[@name='name'])[1]"));}
    public SelenideElement passNewUser(){return $(By.xpath("(//input[@name='password'])[1]"));}
    public SelenideElement passNewUserConfirm(){return $(By.xpath("(//input[@name='passwordConfirm'])[1]"));}
    public SelenideElement phoneNewUser(){return $(By.xpath("(//input[@name='phone'])[1]"));}
    public SelenideElement roleNewUser(){return $(By.xpath("(//span[@class='checkmark'])[6]"));}
    public SelenideElement acceptCreateNewUser(){return $(By.xpath("(//button[@class='addUser_accept'])[2]"));}
    //---------Created User
    public SelenideElement createdUserEmail(){return $(By.xpath("(//div[@class='user_info-mail'])[2]"));}
    public SelenideElement createdUserName(){return $(By.xpath("(//div[@class='user_info-name'])[2]"));}
    public SelenideElement createdUserPhone(){return $(By.xpath("(//div[@class='user_info-phone'])[2]"));}
    //---------Send Invite Button
    public SelenideElement inviteButton(){return $(By.xpath("(//div[@class='bottom'])/div[1]"));}
    //---------Send Invite Pop-up
    public SelenideElement emailForImvite(){return $(By.xpath("(//input[@name='email'])[1]"));}
    public SelenideElement messageForInvite(){return $(By.xpath("//textarea"));}
    public SelenideElement acceptSendInvite(){return $(By.xpath("(//button[@class='inviteUser_accept'])[2]"));}
    public SelenideElement simpleRoleInvite(){return $(By.xpath("(//span[@class='checkmark'])[3]"));}
    //---------First User Info
    public SelenideElement firstUserName (){return $(By.xpath("(//div[@class='user_info-name'])[1]"));}
    public SelenideElement firstUserThreeDots(){return $(By.xpath("(//div[@class='droprown_btn'])[1]"));}
    public SelenideElement firstUserEdit(){return $(By.xpath("(//div[@class='droprown_item'])[1]"));}
    //---------Second User Info
    public SelenideElement secondUserInviteAlert(){return $(By.xpath("//div[@class='user_info-alert']"));}
    //---------- User Pop-up
    public SelenideElement fitstUserOldEmail(){return $(By.xpath("(//input[@name='email'])[3]"));}
    public SelenideElement firstUserOldName(){return $(By.xpath("(//input[@name='name'])[2]"));}
    public SelenideElement firstUserOldPhone(){return $(By.xpath("(//input[@name='phone'])[2]"));}
    public SelenideElement firstUserTimeZone(){return $(By.xpath("//select[@id='timezone']"));}
    public SelenideElement firstUserAcceptNewInfo(){return $(By.xpath("//button[@class='edit_accept' and @type='submit']"));}
    //---------Devices
    public SelenideElement devicesButton(){return $(By.xpath("//div[@class='menu_item-label' and contains(text(),'Устройства')]"));}
    public SelenideElement addDeviceButton(){return $(By.xpath("//div[contains(text(),'ДОБАВИТЬ УСТРОЙСТВО')]"));}
    //---------Device Pop-up
    public SelenideElement newDeviceName(){return $(By.xpath("//input[@placeholder='Название устройства']"));}
    public SelenideElement newDeviceImei(){return $(By.xpath("//input[@placeholder='Imei устройства']"));}
    public SelenideElement newDeviceType(){return $(By.xpath("//select[@formcontrolname='type']"));}
    public SelenideElement newDevicePhone(){return $(By.xpath("//input[@placeholder='телефон']"));}
    public SelenideElement newDevicePass(){return $(By.xpath("//input[@placeholder='access']"));}
    public SelenideElement newDeviceShowPass(){return $(By.xpath("//div[@class='show_pass isVisible']"));}
    public SelenideElement newDeviceApn(){return $(By.xpath("//input[@placeholder='Apn']"));}
    public SelenideElement newDeviceAccept(){return $(By.xpath("//button[contains(text(),'ОТПРАВИТЬ')]"));}
    public SelenideElement newDeviceItem(){return $(By.xpath("(//div[@class='content_item'])[1]"));}
    public SelenideElement newDeviceHandSettings(){return $(By.xpath("//div[@class='checkbox_label' and contains(text(), 'Настроить вручную')]"));}
    //--------- Remove Device
    public SelenideElement removeNewDevice(){return firstUserThreeDots();}
    public SelenideElement removeNewDeviceButton(){return firstUserEdit();}
    public SelenideElement removeNewDeviceConfirm(){return $(By.xpath("//button[contains(text(),'ПОДТВЕРДИТЬ')]"));}
    public SelenideElement contentField(){return $(By.xpath("//div[@class='content ps']"));}
    //---------Menu
    public SelenideElement menuButton(){return $(By.xpath("//div[@class='menuBtn']"));}
    public SelenideElement exitButton(){
        return $(By.xpath("//div[@class='menu_item-label' and contains(text(),'Выход из системы')]"));
    }

}
