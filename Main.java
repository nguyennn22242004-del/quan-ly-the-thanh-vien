import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        QuanLyThanhVien ql = new QuanLyThanhVien();
        Scanner sc = new Scanner(System.in);

        // Thêm sẵn dữ liệu mẫu
        ql.themThanhVien(new ThanhVien("TV01", "Nguyen Van A", "0901234567", 150));
        ql.themThanhVien(new ThanhVien("TV02", "Tran Thi B", "0987654321", 350));

        int luaChon = 0;
        do {
            System.out.println("\n========== QUẢN LÝ THẺ THÀNH VIÊN SIÊU THỊ ==========");
            System.out.println("1. Xem danh sách thành viên");
            System.out.println("2. Thêm thành viên mới");
            System.out.println("3. Tích điểm mua hàng");
            System.out.println("4. Tìm kiếm thành viên theo mã");
            System.out.println("0. Thoát chương trình");
            System.out.print("Chọn chức năng (0-4): ");
            
            luaChon = sc.nextInt();
            sc.nextLine(); // Đọc bỏ dòng thừa

            switch (luaChon) {
                case 1:
                    ql.hienThiDanhSach();
                    break;
                case 2:
                    System.out.print("Nhập mã thẻ: ");
                    String ma = sc.nextLine();
                    System.out.print("Nhập họ tên: ");
                    String ten = sc.nextLine();
                    System.out.print("Nhập SĐT: ");
                    String sdt = sc.nextLine();
                    ql.themThanhVien(new ThanhVien(ma, ten, sdt, 0));
                    break;
                case 3:
                    System.out.print("Nhập mã thẻ cần tích điểm: ");
                    String maTich = sc.nextLine();
                    System.out.print("Nhập số điểm muốn cộng: ");
                    int diem = sc.nextInt();
                    ql.tichDiem(maTich, diem);
                    break;
                case 4:
                    System.out.print("Nhập mã thẻ cần tìm: ");
                    String maTim = sc.nextLine();
                    ThanhVien tv = ql.timKiemTheoMa(maTim);
                    if (tv != null) {
                        tv.hienThiThongTin();
                    } else {
                        System.out.println("Không tìm thấy thành viên!");
                    }
                    break;
                case 0:
                    System.out.println("Cảm ơn bạn đã sử dụng phần mềm!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (luaChon != 0);

        sc.close();
    }
}