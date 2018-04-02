package controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.WriterException;

import model.QrCodeComponent;
import service.QrcodeService;

/**
 * Servlet implementation class QrCodeImageOverlayWeb
 */
public class QrCodeImageOverlayWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	QrCodeComponent qrcode = new QrCodeComponent();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QrCodeImageOverlayWeb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String qrtext = request.getParameter("qrtext");
		qrcode.setQrtext(qrtext);
		String Logo = "./src/main/webapp/resources/image/image.png";
		try {
			byte[] out = QrcodeService.generateQRCodeImageOverlayWebData(qrtext, 175, 175, Logo);
//			byte[] out = QrCodeImage.getQRCodeImage(qrtext, 250,250);
			byte[] encodeBase64 = Base64.getEncoder().encode(out);
			String base64DataString = new String(encodeBase64 , "UTF-8");
			request.setAttribute("output", base64DataString);
			request.setAttribute("input", qrtext);
			request.getRequestDispatcher("index.jsp").forward(request, response);
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
