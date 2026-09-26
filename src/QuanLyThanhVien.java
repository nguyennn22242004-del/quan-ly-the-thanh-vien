import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;

public class QuanLyThanhVien {
    private final String FILE_PATH = "thanhvien.xml";

    // Khởi tạo file XML nếu chưa tồn tại
    public QuanLyThanhVien() {
        try {
            File xmlFile = new File(FILE_PATH);
            if (!xmlFile.exists()) {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.newDocument();
                
                Element rootElement = doc.createElement("DanhSachThanhVien");
                doc.appendChild(rootElement);

                luuFileXML(doc);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khởi tạo file XML: " + e.getMessage());
        }
    }

    // Đọc và hiển thị danh sách từ file XML
    public void hienThiDanhSach() {
        try {
            Document doc = docFileXML();
            NodeList nList = doc.getElementsByTagName("ThanhVien");

            if (nList.getLength() == 0) {
                System.out.println("Danh sách thành viên đang trống!");
                return;
            }

            System.out.println("\n----------------- DANH SÁCH THÀNH VIÊN (XML) -----------------");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nNode;
                    String ma = e.getElementsByTagName("MaThe").item(0).getTextContent();
                    String ten = e.getElementsByTagName("HoTen").item(0).getTextContent();
                    String sdt = e.getElementsByTagName("Sdt").item(0).getTextContent();
                    int diem = Integer.parseInt(e.getElementsByTagName("DiemTichLuy").item(0).getTextContent());

                    ThanhVien tv = new ThanhVien(ma, ten, sdt, diem);
                    System.out.println(tv.toString());
                }
            }
            System.out.println("---------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("Lỗi đọc danh sách XML: " + e.getMessage());
        }
    }

    // Thêm thành viên mới vào XML
    public void themThanhVien(ThanhVien tv) {
        try {
            Document doc = docFileXML();
            Element root = doc.getDocumentElement();

            // Kiểm tra trùng mã thẻ
            if (timElementTheoMa(doc, tv.getMaThe()) != null) {
                System.out.println("Lỗi: Mã thẻ " + tv.getMaThe() + " đã tồn tại trong hệ thống!");
                return;
            }

            Element thanhVien = doc.createElement("ThanhVien");

            Element maThe = doc.createElement("MaThe");
            maThe.appendChild(doc.createTextNode(tv.getMaThe()));
            thanhVien.appendChild(maThe);

            Element hoTen = doc.createElement("HoTen");
            hoTen.appendChild(doc.createTextNode(tv.getHoTen()));
            thanhVien.appendChild(hoTen);

            Element sdt = doc.createElement("Sdt");
            sdt.appendChild(doc.createTextNode(tv.getSdt()));
            thanhVien.appendChild(sdt);

            Element diem = doc.createElement("DiemTichLuy");
            diem.appendChild(doc.createTextNode(String.valueOf(tv.getDiemTichLuy())));
            thanhVien.appendChild(diem);

            root.appendChild(thanhVien);
            luuFileXML(doc);

            System.out.println("Thêm thành viên vào XML thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi thêm thành viên: " + e.getMessage());
        }
    }

    // Tích điểm mua hàng cho thành viên
    public void tichDiem(String maThe, int diemCong) {
        try {
            Document doc = docFileXML();
            Element e = timElementTheoMa(doc, maThe);

            if (e != null) {
                Element elemDiem = (Element) e.getElementsByTagName("DiemTichLuy").item(0);
                int diemHienTai = Integer.parseInt(elemDiem.getTextContent());
                int diemMoi = diemHienTai + diemCong;
                elemDiem.setTextContent(String.valueOf(diemMoi));

                luuFileXML(doc);
                System.out.println("Tích điểm thành công! Số điểm mới của thẻ " + maThe + " là: " + diemMoi);
            } else {
                System.out.println("Không tìm thấy thành viên có mã thẻ: " + maThe);
            }
        } catch (Exception e) {
            System.out.println("Lỗi tích điểm: " + e.getMessage());
        }
    }

    // Tìm kiếm thành viên theo mã
    public void timKiemThanhVien(String maThe) {
        try {
            Document doc = docFileXML();
            Element e = timElementTheoMa(doc, maThe);

            if (e != null) {
                String ma = e.getElementsByTagName("MaThe").item(0).getTextContent();
                String ten = e.getElementsByTagName("HoTen").item(0).getTextContent();
                String sdt = e.getElementsByTagName("Sdt").item(0).getTextContent();
                int diem = Integer.parseInt(e.getElementsByTagName("DiemTichLuy").item(0).getTextContent());

                ThanhVien tv = new ThanhVien(ma, ten, sdt, diem);
                System.out.println("\n--- KẾT QUẢ TÌM KIẾM ---");
                System.out.println(tv.toString());
            } else {
                System.out.println("Không tìm thấy thành viên có mã thẻ: " + maThe);
            }
        } catch (Exception e) {
            System.out.println("Lỗi tìm kiếm: " + e.getMessage());
        }
    }

    // Hàm phụ trợ: Đọc Document XML
    private Document docFileXML() throws Exception {
        File xmlFile = new File(FILE_PATH);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return doc;
    }

    // Hàm phụ trợ: Lưu Document ra file XML
    private void luuFileXML(Document doc) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(FILE_PATH));
        transformer.transform(source, result);
    }

    // Hàm phụ trợ: Tìm Element ThanhVien theo MaThe
    private Element timElementTheoMa(Document doc, String maThe) {
        NodeList nList = doc.getElementsByTagName("ThanhVien");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) nNode;
                String ma = e.getElementsByTagName("MaThe").item(0).getTextContent();
                if (ma.equalsIgnoreCase(maThe)) {
                    return e;
                }
            }
        }
        return null;
    }
}
