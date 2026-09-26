public class ThanhVien {
    private String maThe;
    private String hoTen;
    private String sdt;
    private int diemTichLuy;

    public ThanhVien() {
    }

    public ThanhVien(String maThe, String hoTen, String sdt, int diemTichLuy) {
        this.maThe = maThe;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.diemTichLuy = diemTichLuy;
    }

    public String getMaThe() {
        return maThe;
    }

    public void setMaThe(String maThe) {
        this.maThe = maThe;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(int diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }

    @Override
    public String toString() {
        return String.format("Mã thẻ: %-8s | Họ tên: %-20s | SĐT: %-11s | Điểm: %d", 
                maThe, hoTen, sdt, diemTichLuy);
    }
}