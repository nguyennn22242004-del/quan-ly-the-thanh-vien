import java.util.ArrayList;

public class QuanLyThanhVien {
    private ArrayList<ThanhVien> danhSach = new ArrayList<>();

    // 1. Thêm thành viên mới
    public void themThanhVien(ThanhVien tv) {
        danhSach.add(tv);
        System.out.println("-> Thêm thành viên thành công!");
    }

    // 2. Hiển thị danh sách
    public void hienThiDanhSach() {
        if (danhSach.isEmpty()) {
            System.out.println("Danh sách thẻ thành viên trống!");
            return;
        }
        System.out.println("\n--- DANH SÁCH THẺ THÀNH VIÊN SIÊU THỊ ---");
        for (ThanhVien tv : danhSach) {
            tv.hienThiThongTin();
        }
    }

    // 3. Tìm kiếm theo mã thẻ
    public ThanhVien timKiemTheoMa(String maThe) {
        for (ThanhVien tv : danhSach) {
            if (tv.getMaThe().equalsIgnoreCase(maThe)) {
                return tv;
            }
        }
        return null;
    }

    // 4. Tích điểm cho thành viên
    public void tichDiem(String maThe, int diem) {
        ThanhVien tv = timKiemTheoMa(maThe);
        if (tv != null) {
            tv.congDiem(diem);
            System.out.println("-> Đã cộng " + diem + " điểm cho thẻ " + maThe);
        } else {
            System.out.println("-> Không tìm thấy mã thẻ: " + maThe);
        }
    }
}