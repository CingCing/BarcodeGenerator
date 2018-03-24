package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.WriterException;

import model.QrCodeComponent;
import service.QrCodeImage;

/**
 * Servlet implementation class generateQRCodeImage
 */
@WebServlet("/generateQRCodeImage")
public class generateQRCodeImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	QrCodeComponent qrcode = new QrCodeComponent();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public generateQRCodeImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
		
		String qrtext = request.getParameter("qrtext");
		qrcode.setQrtext(qrtext);
//		String text = qrcode.getQrtext();
//		String image_path = "./src/main/webapp/resources/image/" + text + ".png";
		request.setAttribute("input", qrtext);
		
		try {
//			QrCodeImage.generateQRCodeImage(text, 175, 175, image_path);
			byte[] out = QrCodeImage.getQRCodeImage(qrtext, 250,250);
			byte[] encodeBase64 = Base64.getEncoder().encode(out);
			String base64DataString = new String(encodeBase64 , "UTF-8");
			request.setAttribute("output", base64DataString);
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
