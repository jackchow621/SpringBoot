package ghost.springboot;

import ghost.springboot.pub.MailConstant;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootTests {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootTests.class);
	
	@Autowired
    private JavaMailSenderImpl mailSender;
	
	@Test
	public void contextLoads() {
		
	}
	
	 /**
     * 发送包含简单文本的邮件
     */
    @Test
    public void sendTxtMail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人
        simpleMailMessage.setTo(MailConstant.RECEIVE_MAIL_ADDRESS);
        simpleMailMessage.setFrom(MailConstant.SEND_MAIL_ADDRESS);
        simpleMailMessage.setSubject("Spring Boot Mail 邮件测试【文本】");
        simpleMailMessage.setText("这里是一段简单文本。");
        // 发送邮件
        mailSender.send(simpleMailMessage);

        LOGGER.info(MailConstant.SEND_MAIL_SUCCESS);
    }

    /**
     * 发送包含HTML文本的邮件
     * @throws Exception
     */
    @Test
    public void sendHtmlMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(MailConstant.RECEIVE_MAIL_ADDRESS);
        mimeMessageHelper.setFrom(MailConstant.SEND_MAIL_ADDRESS);
        mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【HTML】");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p></body>");
        sb.append("</html>");

        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);
        // 发送邮件
        mailSender.send(mimeMessage);

        LOGGER.info(MailConstant.SEND_MAIL_SUCCESS);

    }

    /**
     * 发送包含内嵌图片的邮件
     * @throws Exception
     */
    @Test
    public void sendAttachedImageMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(MailConstant.RECEIVE_MAIL_ADDRESS);
        mimeMessageHelper.setFrom(MailConstant.SEND_MAIL_ADDRESS);
        mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【图片】");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p>");
        // cid为固定写法，imageId指定一个标识
        sb.append("<img src=\"cid:imageId\"/></body>");
        sb.append("</html>");

        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);

        // 设置imageId
        FileSystemResource img = new FileSystemResource(new File("D:/CrackCaptcha.log"));
        mimeMessageHelper.addInline("imageId", img);

        // 发送邮件
        mailSender.send(mimeMessage);

        LOGGER.info(MailConstant.SEND_MAIL_SUCCESS);
    }

    /**
     * 发送包含附件的邮件
     * @throws Exception
     */
    @Test
    public void sendAttendedFileMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        mimeMessageHelper.setTo(MailConstant.RECEIVE_MAIL_ADDRESS);
        mimeMessageHelper.setFrom(MailConstant.SEND_MAIL_ADDRESS);
        mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【附件】");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p></body>");
        sb.append("</html>");

        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);
        // 设置附件
        FileSystemResource log = new FileSystemResource(new File("D:/CrackCaptcha.log"));
        mimeMessageHelper.addAttachment("CrackCaptcha.log", log);

        // 发送邮件
        mailSender.send(mimeMessage);

        LOGGER.info(MailConstant.SEND_MAIL_SUCCESS);
    }
}
