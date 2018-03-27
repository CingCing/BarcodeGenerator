package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.WriterException;

import model.QrCodeComponent;
import service.QrcodeService;

/**
 * Servlet implementation class QrCodeImageOverlay
 */
public class QrCodeImageOverlay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	QrCodeComponent qrcode = new QrCodeComponent();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QrCodeImageOverlay() {
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
		String filePath = "./src/main/webapp/resources/image/";
		String Logo = "./src/main/webapp/resources/image/image.png";
		try {
			QrcodeService.generateQRCodeImageOverlay(qrtext, 175, 175, filePath, Logo);
			request.setAttribute("input", qrtext);
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
