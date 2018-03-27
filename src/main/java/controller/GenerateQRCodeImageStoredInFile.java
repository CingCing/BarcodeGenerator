package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.WriterException;

import dao.DBConnect;
import model.QrCodeComponent;
import service.QrCodeImage;

/**
 * Servlet implementation class GenerateQRCodeImageStoredInFile
 */
@WebServlet("/GenerateQRCodeImageStoredInFile")
public class GenerateQRCodeImageStoredInFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	QrCodeComponent qrcode = new QrCodeComponent();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateQRCodeImageStoredInFile() {
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
		String image_path = "./src/main/webapp/resources/image/" + qrtext + ".png";
		request.setAttribute("input", qrtext);
		
		try {
			DBConnect.insert(qrtext);
			QrCodeImage.generateQRCodeImage(qrtext, 175, 175, image_path);
//			byte[] out = QrCodeImage.getQRCodeImage(qrtext, 250,250);
//			byte[] encodeBase64 = Base64.getEncoder().encode(out);
//			String base64DataString = new String(encodeBase64 , "UTF-8");
//			request.setAttribute("output", base64DataString);
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
