import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QuanLyThanhVien ql = new QuanLyThanhVien();
        int luaChon = -1;

        do {
            System.out.println("\n--------- QUẢN LÝ THẺ THÀNH VIÊN SIÊU THỊ (XML) ---------");
            System.out.println("1. Xem danh sách thành viên");
            System.out.println("2. Thêm thành viên mới");
            System.out.println("3. Tích điểm mua hàng");
            System.out.println("4. Tìm kiếm thành viên theo mã");
            System.out.println("0. Thoát chương trình");
            System.out.print("Chọn chức năng (0-4): ");

            try {
                luaChon = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                luaChon = -1;
            }

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
                    int diem = Integer.parseInt(sc.nextLine());
                    ql.tichDiem(maTich, diem);
                    break;
                case 4:
                    System.out.print("Nhập mã thẻ cần tìm: ");
                    String maTim = sc.nextLine();
                    ql.timKiemThanhVien(maTim);
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình. Cảm ơn bạn!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
            }
        } while (luaChon != 0);

        sc.close();
    }
}
