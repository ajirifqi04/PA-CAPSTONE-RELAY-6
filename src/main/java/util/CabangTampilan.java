package util;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

final class CabangTampilan {
    private CabangTampilan() {}
    private static final Color PALE = new Color(232,242,255);

    static void rapikan(JFrame frame, JPanel sidebar, JLabel logo, JLabel tagline,
                        JButton dash, JButton cabang, JButton terima, JButton agen,
                        JButton keluar, JSeparator separator,
                        JPanel content, JLabel pageTitle, JLabel pageInfo,
                        JPanel data, JLabel dataTitle, JLabel description,
                        JTextField search, JButton btnCari, JComboBox<?> filter,
                        JButton btnFilter, JButton refresh, JButton detail,
                        JScrollPane tableScroll,
                        JLabel formTitle, JLabel formHelp, JComboBox<?> status, JButton primary,
                        JLabel dateLabel, JTextField dateInput,
                        JLabel etaLabel, JTextField etaInput) {
        tataSidebar(sidebar,logo,tagline,dash,cabang,terima,agen,keluar,separator);
        rapikanTombol(btnCari, 84); rapikanTombol(btnFilter, 90);
        rapikanTombol(refresh, 105); rapikanTombol(detail, 130);
        rapikanTombol(primary, 164);
        filter.setPreferredSize(new Dimension(235,34));
        filter.setMinimumSize(new Dimension(215,34));
        status.setPreferredSize(new Dimension(235,36));
        status.setMinimumSize(new Dimension(215,36));
        filter.setToolTipText("Pilih status, kemudian klik Filter untuk menampilkan hasil");
        data.setBackground(PALE);
        data.removeAll();
        data.setLayout(new BorderLayout(0,11));
        data.setBorder(new EmptyBorder(18,20,17,20));

        JPanel head = new JPanel(); head.setOpaque(false);
        head.setLayout(new BoxLayout(head,BoxLayout.Y_AXIS));
        JPanel line1 = new JPanel(new BorderLayout(8,0)); line1.setOpaque(false);
        line1.add(dataTitle,BorderLayout.WEST);
        JPanel searchBar = new JPanel(new FlowLayout(FlowLayout.RIGHT,7,0)); searchBar.setOpaque(false);
        search.setPreferredSize(new Dimension(190,34));
        searchBar.add(search); searchBar.add(btnCari);
        line1.add(searchBar,BorderLayout.EAST);
        head.add(line1);
        JPanel line2 = new JPanel(new BorderLayout(8,0)); line2.setOpaque(false);
        description.setForeground(new Color(72,88,112));
        line2.add(description,BorderLayout.WEST);
        JPanel filterBar = new JPanel(new FlowLayout(FlowLayout.RIGHT,7,0)); filterBar.setOpaque(false);
        filterBar.add(filter); filterBar.add(btnFilter);
        line2.add(filterBar,BorderLayout.EAST);
        head.add(Box.createVerticalStrut(8));
        head.add(line2);
        data.add(head,BorderLayout.NORTH);

        tableScroll.setBorder(BorderFactory.createLineBorder(new Color(207,220,236)));
        tableScroll.setPreferredSize(new Dimension(690,256));
        tableScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        tableScroll.getHorizontalScrollBar().setUnitIncrement(20);
        data.add(tableScroll,BorderLayout.CENTER);

        JPanel bottom=new JPanel(); bottom.setOpaque(false);
        bottom.setLayout(new BoxLayout(bottom,BoxLayout.Y_AXIS));
        JPanel controls=new JPanel(new FlowLayout(FlowLayout.RIGHT,8,0));controls.setOpaque(false);
        controls.add(refresh);controls.add(detail);
        bottom.add(controls);bottom.add(Box.createVerticalStrut(9));
        JPanel form=new JPanel();form.setOpaque(false);
        form.setLayout(new BoxLayout(form,BoxLayout.Y_AXIS));
        formTitle.setFont(new Font("Segoe UI",Font.BOLD,16));
        formTitle.setForeground(new Color(24,48,86));
        formTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        form.add(formTitle);
        form.add(Box.createVerticalStrut(5));
        if(dateInput!=null && etaInput!=null) {
            
            JPanel dates=new JPanel(new GridLayout(1,2,12,0));dates.setOpaque(false);
            JPanel datePanel=field(dateLabel,dateInput);
            JPanel etaPanel=field(etaLabel,etaInput);
            dates.add(datePanel);dates.add(etaPanel);
            dates.setAlignmentX(Component.LEFT_ALIGNMENT);
            form.add(dates);form.add(Box.createVerticalStrut(7));
        } else {
            formHelp.setForeground(new Color(75,89,106));
            formHelp.setAlignmentX(Component.LEFT_ALIGNMENT);
            form.add(formHelp);form.add(Box.createVerticalStrut(7));
        }
        JPanel statusRow = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));statusRow.setOpaque(false);
        if(dateInput!=null) statusRow.add(formHelp);
        statusRow.add(status);statusRow.add(primary);
        statusRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        form.add(statusRow);
        form.setAlignmentX(Component.LEFT_ALIGNMENT);
        bottom.add(form);
        data.add(bottom,BorderLayout.SOUTH);

        content.removeAll();content.setLayout(new BorderLayout(0,14));
        content.setBorder(new EmptyBorder(20,22,18,22));
        content.setBackground(Color.WHITE);
        JPanel heading=new JPanel(); heading.setOpaque(false);
        heading.setLayout(new BoxLayout(heading,BoxLayout.Y_AXIS));
        pageTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        pageInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        heading.add(pageTitle);heading.add(Box.createVerticalStrut(4));heading.add(pageInfo);
        content.add(heading,BorderLayout.NORTH);
        content.add(data,BorderLayout.CENTER);
        Container root=frame.getContentPane();
        root.removeAll();root.setLayout(new BorderLayout(0,0));
        root.add(sidebar,BorderLayout.WEST);root.add(content,BorderLayout.CENTER);
        root.setPreferredSize(new Dimension(1010,dateInput==null?646:672));
        root.revalidate();root.repaint();
    }

    private static JPanel field(JLabel label,JTextField input){
        JPanel p=new JPanel(new BorderLayout(0,4));p.setOpaque(false);
        label.setFont(new Font("Segoe UI",Font.PLAIN,12));
        p.add(label,BorderLayout.NORTH);
        input.setPreferredSize(new Dimension(210,33));
        p.add(input,BorderLayout.CENTER);
        return p;
    }
    private static void rapikanTombol(JButton b,int width){
        int needed=b.getFontMetrics(b.getFont()).stringWidth(b.getText())+26;
        b.setPreferredSize(new Dimension(Math.max(width,needed),36));
        b.setMinimumSize(new Dimension(Math.max(width,needed),36));
        b.setToolTipText(b.getText());
    }
    private static void tataSidebar(JPanel sidebar,JLabel logo,JLabel tagline,
                                    JButton dash,JButton cabang,JButton terima,JButton agen,
                                    JButton keluar,JSeparator separator){
        sidebar.removeAll();sidebar.setLayout(new GridBagLayout());
        sidebar.setBackground(new Color(26,65,126));
        sidebar.setPreferredSize(new Dimension(238,646));
        cabang.setText("Pengiriman ke Cabang");
        agen.setText("Pengiriman ke Agen");
        JButton[] b={dash,cabang,terima,agen,keluar};
        for(JButton x:b){x.setPreferredSize(new Dimension(207,42));x.setMinimumSize(new Dimension(207,42));x.setToolTipText(x.getText());}
        GridBagConstraints g=new GridBagConstraints();
        g.gridx=0;g.fill=GridBagConstraints.HORIZONTAL;g.weightx=1;
        g.insets=new Insets(4,15,4,15);
        g.gridy=0;g.insets=new Insets(22,19,0,19);sidebar.add(logo,g);
        g.gridy++;g.insets=new Insets(4,19,16,19);sidebar.add(tagline,g);
        for(int i=0;i<4;i++) {g.gridy++;g.insets=new Insets(4,15,4,15);sidebar.add(b[i],g);}
        g.gridy++;g.insets=new Insets(14,18,12,18);sidebar.add(separator,g);
        g.gridy++;g.insets=new Insets(0,15,4,15);sidebar.add(keluar,g);
        g.gridy++;g.weighty=1;g.fill=GridBagConstraints.BOTH;sidebar.add(Box.createGlue(),g);
    }
}
