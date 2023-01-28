package Pdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class App {
  private static final String String = null;
PDDocument invoice;
  int s;
  Integer totalInvoice = 0;
  Integer PriceInv;
  String CustomerName;
  String CustomerPh;
  List<String> Product_Name = new ArrayList<String>();
  List<Integer> Product_Price = new ArrayList<Integer>();
  List<Integer> Product_Qty = new ArrayList<Integer>();
  String Invoice_Title = new String("Asterisc Technology Private Limited");
  String Sub_Title = new String("Invoice");
  
  
  App() {
    
    invoice = new PDDocument();
    
    PDPage NewPage = new PDPage();
    
    invoice.addPage(NewPage);
  }
  
  
  void getdata() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the Customer Name: ");
    CustomerName = sc.nextLine();
    System.out.println("Enter the Customer Phone Number: ");
    CustomerPh = sc.nextLine();
    System.out.println("Enter the Number of Products: ");
    s = sc.nextInt();
    System.out.println();
    for(int i=0; i<s; i++) {
      System.out.println("Enter the Product Name: ");
      Product_Name.add(sc.next());
      System.out.println("Enter the Price of the Product: ");
      Product_Price.add(sc.nextInt());
      System.out.println("Enter the Quantity of the Product: ");
      Product_Qty.add(sc.nextInt());
      System.out.println();
      //Calculating the total amount
      totalInvoice = totalInvoice + (Product_Price.get(i)*Product_Qty.get(i));
    }
  }
  
  void WriteInvoice() {
    //get the page
    PDPage MyPage = invoice.getPage(0);
    try {
      //Prepare Content Stream
      PDPageContentStream cs = new PDPageContentStream(invoice, MyPage);
      
      //Writing Single Line text
      //Writing the Invoice title
      cs.beginText();
      cs.setFont(PDType1Font.TIMES_ROMAN, 20);
      cs.newLineAtOffset(140, 750);
      cs.showText(Invoice_Title);
      cs.endText();
      
      cs.beginText();
      cs.setFont(PDType1Font.TIMES_ROMAN, 18);
      cs.newLineAtOffset(270, 690);
      cs.showText(Sub_Title);
      cs.endText();
      
      //Writing Multiple Lines
      //writing the customer details
      cs.beginText();
      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
      cs.setLeading(20f);
      cs.newLineAtOffset(60, 610);
      cs.showText("Customer Name: ");
      cs.newLine();
      cs.showText("Phone Number: ");
      cs.endText();
      
      cs.beginText();
      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
      cs.setLeading(20f);
      cs.newLineAtOffset(170, 610);
      cs.showText(CustomerName);
      cs.newLine();
      cs.showText(CustomerPh);
      cs.endText();
      
      cs.beginText();
      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
      cs.newLineAtOffset(80, 540);
      cs.showText("Product Name");
      cs.endText();
      
      cs.beginText();
      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
      cs.newLineAtOffset(200, 540);
      cs.showText("Unit Price");
      cs.endText();
      
      cs.beginText();
      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
      cs.newLineAtOffset(310, 540);
      cs.showText("Quantity");
      cs.endText();
      
      cs.beginText();
      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
      cs.newLineAtOffset(410, 540);
      cs.showText("Price");
      cs.endText();
      
      cs.beginText();
      cs.setFont(PDType1Font.TIMES_ROMAN, 12);
      cs.setLeading(20f);
      cs.newLineAtOffset(80, 520);
      for(int i =0; i<s; i++) {
        cs.showText(Product_Name.get(i));
        cs.newLine();
      }
      cs.endText();
      
      cs.beginText();
      cs.setFont(PDType1Font.TIMES_ROMAN, 12);
      cs.setLeading(20f);
      cs.newLineAtOffset(200, 520);
      for(int i =0; i<s; i++) {
        cs.showText(Product_Price.get(i).toString());
        cs.newLine();
      }
      cs.endText();
      
      cs.beginText();
      cs.setFont(PDType1Font.TIMES_ROMAN, 12);
      cs.setLeading(20f);
      cs.newLineAtOffset(310, 520);
      for(int i =0; i<s; i++) {
        cs.showText(Product_Qty.get(i).toString());
        cs.newLine();
      }
      cs.endText();
      
      cs.beginText();
      cs.setFont(PDType1Font.TIMES_ROMAN, 12);
      cs.setLeading(20f);
      cs.newLineAtOffset(410, 520);
      for(int i =0; i<s; i++) {
        PriceInv = Product_Price.get(i)*Product_Qty.get(i);
        cs.showText(PriceInv.toString());
        cs.newLine();
      }
      cs.endText();
      
      cs.beginText();
      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
      cs.newLineAtOffset(310, (500-(20*s)));
      cs.showText("Total: ");
      cs.endText();
      
      cs.beginText();
      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
      //Calculating where total is to be written using number of products
      cs.newLineAtOffset(410, (500-(20*s)));
      cs.showText(totalInvoice.toString());
      cs.endText();
      
      //Close the content stream
      cs.close();
      //Save the PDF
      //invc.save("C:\Users\rajla\Documents\eclipse-workspace\Pdf");
      invoice.save("INVOICE.Pdf");
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  
  public static void main(String args[]) {
    App N = new App();
    N.getdata();
    N.WriteInvoice();
    System.out.println("Invoice Generated!");
  }
}