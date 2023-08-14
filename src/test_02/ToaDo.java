
package test_02;
// Tạo đối tượng tọa độ để lưu vị trí của điểm bắt đầu, xuất phát, vị trí của các thức ăn
public class ToaDo {
    private String Ten;
    private int x;
    private int y;

    public ToaDo() {
    }

    public ToaDo(String Ten, int x, int y) {
        this.Ten = Ten;
        this.x = x;
        this.y = y;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    // Hàm tính khoảng cách giữa 2 điểm
    public  int KhoangCach(ToaDo x){
        return (int) Math.sqrt(Math.pow(this.getX() - x.getX(), 2) + Math.pow(this.getY() - x.getY(), 2));
    }
    
    // Hàm mô tả đường đi của con rắn
    // -  TH1: Nếu hai điểm // với õ với oy thì đi theo đường thẳng 
    // -  TH2: Tạo ra 1 điểm trung gian có x = x của điểm xuất phát , y = y của điểm đến => quay lại trường hợp 1 
    public void DuongDi(ToaDo toado){
        
        // trường hợp 1 hai điểm tạo thành đường thẳng // với OY
        if (this.getX() == toado.getX()) {
            // điểm cần đếm nằm ở bên trên
            if(this.getY() < toado.getY()){
                System.out.println("Từ điểm " + this.getTen() + "(" + this.getX() +"," + this.getY() + ") sang bên trên đến điểm " +  toado.getTen() + "(" + toado.getX() +"," + toado.getY() + ")");
            }
            // điểm cần đếm nằm ở bên dưới
            else{
                System.out.println("Từ điểm " + this.getTen() + "(" + this.getX() +"," + this.getY() + ") sang bên dưới đến điểm " +  toado.getTen() + "(" + toado.getX() +"," + toado.getY() + ")");
            }
        }
        
        // trường hợp 2 hai điểm tạo thành đường thẳng // với OX
        
        else if (this.getY() == toado.getY()) {
            // điểm cần đếm nằm ở bên phải
            if (this.getX() < toado.getX()) {
                System.out.println("Từ điểm " + this.getTen() + "(" + this.getX() +"," + this.getY() + ") sang bên phải đến điểm " +  toado.getTen() + "(" + toado.getX() +"," + toado.getY() + ")");
            }
            // điểm cần đến nằm ở bên trái
            else{
                System.out.println("Từ điểm " + this.getTen() + "(" + this.getX() +"," + this.getY() + ") sang bên trái đến điểm " +  toado.getTen() + "(" + toado.getX() +"," + toado.getY() + ")");
            }
        }
        // trường hợp hai điểm không // với OX VÀ OY
        else{
            ToaDo trunggian = new ToaDo("trung gian" , this.getX(), toado.getY());
            DuongDi(trunggian);
            trunggian.DuongDi(toado);
            
        }
    }
}
