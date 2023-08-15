package test_02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Nhập thông tin của điểm bắt đầu và kết thúc
        System.out.println("Nhập tọa độ điểm bắt đầu");
        System.out.println("x = ");
        int XBatDau = scanner.nextInt();
        System.out.println("y = ");
        int YBatDau = scanner.nextInt();
        ToaDo batdau = new ToaDo("Bắt đầu", XBatDau, YBatDau);
        System.out.println("Nhập tọa độ điểm kết thúc");
        System.out.println("x = ");
        int XKetThuc = scanner.nextInt();
        System.out.println("y = ");
        int YKetThuc = scanner.nextInt();
        ToaDo KetThuc = new ToaDo("Kết thúc", XKetThuc, YKetThuc);

        // Nhập số lượng thức ăn và thông tin tọa độ
        System.out.println("Nhập số lượng thức ăn");
        int soluong = scanner.nextInt();
        List<ToaDo> z = new ArrayList<>();
        for (int i = 0; i < soluong; i++) {
            System.out.println("Nhập tọa độ thức ăn số " + (i + 1));
            System.out.println("x = ");
            int x = scanner.nextInt();
            System.out.println("y = ");
            int y = scanner.nextInt();
            int ten = i+1;
            ToaDo itemp = new ToaDo("thức ăn số " + ten, x, y);

            z.add(itemp);

        }
        var TatatCaDuongDi = taoHoanVi(z);
        System.out.println("Có tât cả: " + z.size() + "! cách đi:");
        for(List<ToaDo> ab : TatatCaDuongDi){
            ab.add(KetThuc);
            ab.add(0, batdau);
            for (ToaDo ac : ab) {
                System.out.print( ac.getTen()  );
            }
            System.out.print("Tổng đường đi = " + QuangDuong(ab));
            System.out.println("");
        }
        List<ToaDo> duongDiNganNhat = DuongDiNganNhat(taoHoanVi(z),batdau,KetThuc);
         
        //var sapxep = listToaDo(batdau, KetThuc, z);

        System.out.println("Đường đi ngắn nhất là :");
        for (int i = 0; i < duongDiNganNhat.size(); i++) {
            System.out.println(duongDiNganNhat.get(i).getTen() + " ==> ");
        }
        System.out.println("Tổng đường đi = " + QuangDuong(duongDiNganNhat));
        System.out.println("chitiet: ");
        for (int i = 0; i < duongDiNganNhat.size() - 1; i++) {
            duongDiNganNhat.get(i).DuongDi(duongDiNganNhat.get(i + 1));
        }
    }

    public static int QuangDuong(List<ToaDo> x) {
        int quangduong = 0;
        for (int i = 0; i < x.size() - 1; i++) {
            quangduong +=  x.get(i).KhoangCach(x.get(i + 1));
        }
        return quangduong;
    }
// tìm ra đường đi ngắn nhất trong tất cả 
    public static List<ToaDo> DuongDiNganNhat(List<List<ToaDo>> hoanvi, ToaDo BatDau, ToaDo KetThuc) {
        for (List<ToaDo> list : hoanvi) {
            list.add(0, BatDau);
            list.add(KetThuc);
        }
        List<ToaDo> min = new ArrayList<>(hoanvi.get(0)); 
        for (int i = 0; i < hoanvi.size(); i++) {
            if (QuangDuong(hoanvi.get(i)) < QuangDuong(min)) {               
                min = new ArrayList<>(hoanvi.get(i)); 
            }
        }
        return min;
    }
// Tạo danh sách tất cả hoán vị của đường đi
    public static List<List<ToaDo>> taoHoanVi(List<ToaDo> toadoList) {
        List<List<ToaDo>> hoanVi = new ArrayList<>();
        taoHoanVi(toadoList, new ArrayList<>(), hoanVi);
        return hoanVi;
    }

    // Hàm đệ quy để tạo hoán vị
    private static void taoHoanVi(List<ToaDo> conLai, List<ToaDo> hienTai, List<List<ToaDo>> hoanVi) {

        if (conLai.isEmpty()) {
            hoanVi.add(new ArrayList<>(hienTai)); 
            return;
        }

        
        for (int i = 0; i < conLai.size(); i++) {
            ToaDo so = conLai.get(i); 
            hienTai.add(so); 
            conLai.remove(i); 

            
            taoHoanVi(conLai, hienTai, hoanVi);

            conLai.add(i, so); 
            hienTai.remove(hienTai.size() - 1); 
        }
    }
    
    
// cách cũ
    // hàm tìm đường đi ngắn nhất bằng phương pháp so sánh khoảng cách từ điểm xuất phát đến điểm lân cận gần nhất 
    //  B1: Lặp qua từng điểm trong danh sách
    //  B2: Trong mỗi vòng lặp, lặp qua các điểm còn lại trong danh sách
    //  B3: So sánh khoảng cách giữa điểm đang xét i đến i +1 với khoảng cách giữa điểm đang xét i và điểm sau nó trong danh sách j
    // nếu khoảng cách giữa điểm đang xét i đến đến j < khoảng cách giữa i đến i +1 thì đổi vị trí của j với i+1 
    public static List<ToaDo> listToaDo(ToaDo batdau, ToaDo ketthuc, List<ToaDo> listtoado) {

        listtoado.add(0, batdau);

        // Sử dụng thuật toán sắp xếp nổi bọt
        for (int i = 0; i < listtoado.size() - 1; i++) {
            for (int j = i + 1; j <= listtoado.size() - 1; j++) {

                // so sánh d(i,j) với d(i,i+1) nếu phát hiện khoảng cách d(i,j) < thì đổi chỗ i với j =< kết thúc vòng lặp thứ 2
                // điểm có khoảng cách gần nhất sẽ ở vị trí i +1 , tiếp tục so sánh đến khi kết thúc vòng lặp thứ nhất
                if (listtoado.get(i).KhoangCach(listtoado.get(j)) < listtoado.get(i).KhoangCach(listtoado.get(i + 1))) {
                    ToaDo min = listtoado.get(j);
                    listtoado.set(j, listtoado.get(i + 1));
                    listtoado.set(i + 1, min);
                }

            }

        }
        listtoado.add(ketthuc);
        return listtoado;
    }
}
