import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Dell on 2018/10/10.
 */
public class MailTest {
    //发件人邮箱和密码
    public static String sendEmail="1747100270@qq.com";
    public static String sendPassword="lsx18772116092";

    //发件人邮箱的SMTP服务器地址，网易126邮箱的SMTP服务器地址为smtp.126.com   qq邮箱的smtp服务器地址为smtp.qq.com
    public static String EmailSMTPHost="smtp.qq.com";

    public static String receiveAccount="731861302@qq.com";

    public static void main(String[] args)throws Exception {
        //用于连接邮件服务器的参数配置
        Properties props=new Properties();
        props.setProperty("mail.transport.protocol","smtp");  //使用协议(JavaMail规范要求)
        props.setProperty("mail.smtp.host",EmailSMTPHost);  //发件人的SMTP服务器地址
        props.setProperty("mail.smtp.auth","true");     //需要请求认证

        //根据配置创建会话对象，用于和邮件服务器交互
        Session session=Session.getInstance(props);
        //设置为debug模式，可以船好看详细的发送log
        session.setDebug(true);
        //创建一封邮件
        MimeMessage message=createMimeMessage(session,sendEmail,receiveAccount);

        Transport transport=session.getTransport();

        transport.connect(sendEmail,sendPassword);
        transport.sendMessage(message,message.getAllRecipients());
        transport.close();
    }

    /**
     * 创建一封只包含文本的简单邮件
     * @param session
     * @param sendMail
     * @param receiveMail
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session,String sendMail,String receiveMail) throws Exception{
        //1.创建一封邮件
        MimeMessage message=new MimeMessage(session);
        //2.From:发件人
        message.setFrom(new InternetAddress(sendMail,"好友祝福","UTF-8"));
        //3.To:收件人
        message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(receiveMail,"xx用户","UTF-8"));
        message.setSubject("主题","UTF-8");
        message.setContent("","text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
}












