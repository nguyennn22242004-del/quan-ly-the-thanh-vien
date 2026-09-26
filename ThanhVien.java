public class ThanhVien {
    private String maThe;
    private String hoTen;
    private String soDienThoai;
    private int diemTichLuy;

    // Constructor (Khởi tạo)
    public ThanhVien(String maThe, String hoTen, String soDienThoai, int diemTichLuy) {
        this.maThe = maThe;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.diemTichLuy = diemTichLuy;
    }

    // Getters & Setters
    public String getMaThe() { return maThe; }
    public String getHoTen() { return hoTen; }
    public String getSoDienThoai() { return soDienThoai; }
    public int getDiemTichLuy() { return diemTichLuy; }

    public void congDiem(int diem) {
        this.diemTichLuy += diem;
    }

    // Hạng thành viên dựa trên điểm
    public String getHangThe() {
        if (diemTichLuy >= 500) return "Kim Cương";
        if (diemTichLuy >= 200) return "Vàng";
        return "Bạc";
    }

    public void hienThiThongTin() {
        System.out.printf("Mã thẻ: %-8s | Tên: %-18s | SĐT: %-10s | Điểm: %-5d | Hạng: %s\n",
                maThe, hoTen, soDienThoai, diemTichLuy, getHangThe());
    }
}