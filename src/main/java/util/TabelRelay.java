package util;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public final class TabelRelay {
    private TabelRelay() {}
    public static void pasang(JTable tabel) {
        tabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabel.setRowHeight(36);
        tabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabel.setFont(new Font("Segoe UI",Font.PLAIN,12));
        tabel.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,12));
        tabel.getTableHeader().setReorderingAllowed(false);
        tabel.setShowVerticalLines(false);
        tabel.setDefaultRenderer(Object.class,new DefaultTableCellRenderer(){
            @Override public Component getTableCellRendererComponent(JTable t,Object value, boolean selected, boolean focused,int row,int column){
                Component c=super.getTableCellRendererComponent(t,value,selected,focused,row,column);
                if(selected) return c;
                String heading=t.getColumnName(column).toLowerCase();
                if(heading.contains("status") && value!=null){
                    String v=value.toString().toLowerCase();
                    if(v.contains("selesai")||v.contains("diterima")||v.contains("sampai")) c.setForeground(new Color(16,120,67));
                    else if(v.contains("batal")) c.setForeground(new Color(185,40,50));
                    else if(v.contains("jalan")||v.contains("proses")) c.setForeground(new Color(39,83,185));
                    else c.setForeground(new Color(167,112,0));
                } else c.setForeground(new Color(20,33,61));
                c.setBackground(row%2==0 ? Color.WHITE : new Color(248,250,254));
                return c;
            }
        });
        aturLebar(tabel);
        tabel.getModel().addTableModelListener(e -> SwingUtilities.invokeLater(() -> aturLebar(tabel)));
    }

    public static void aturLebarPenuh(JTable tabel, JScrollPane scroll) {
        if(tabel.getColumnCount()==0 || scroll==null)return;
        aturLebar(tabel);
        int target=Math.max(0,scroll.getViewport().getWidth()-3);
        int width=0;
        for(int i=0;i<tabel.getColumnCount();i++) width+=tabel.getColumnModel().getColumn(i).getPreferredWidth();
        if(target>width){
            int base=(target-width)/tabel.getColumnCount();
            int extra=(target-width)%tabel.getColumnCount();
            for(int i=0;i<tabel.getColumnCount();i++){
                javax.swing.table.TableColumn col=tabel.getColumnModel().getColumn(i);
                col.setPreferredWidth(col.getPreferredWidth()+base+(i<extra?1:0));
            }
        }
        tabel.getTableHeader().revalidate();
        tabel.revalidate();
    }
    public static void aturLebar(JTable t){
        for(int i=0;i<t.getColumnCount();i++){
            String h=t.getColumnName(i).toLowerCase();
            int w=h.contains("nama")||h.contains("agen")||h.contains("alamat") ? 180 :
                    h.contains("tanggal")||h.contains("resi") ? 160 :
                    h.contains("status") ? 145 : h.contains("id") ? 95 : 135;
            t.getColumnModel().getColumn(i).setPreferredWidth(w);
        }
    }
}
