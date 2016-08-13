package Serface;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Manages.ManageGoods;
import Util.Goods;

public class AddDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	JOptionPane option = new JOptionPane();

	private JLabel nameLabel = new JLabel("��Ʒ����");
	private JLabel numLabel = new JLabel("��Ʒ���");
	private JLabel sumLabel = new JLabel("��Ʒ����");
	private JLabel priceLabel = new JLabel("��Ʒ����");

	private JTextField nameText = new JTextField(10);
	private JTextField numText = new JTextField(10);
	private JTextField sumText = new JTextField(10);
	private JTextField priceText = new JTextField(10);

	private JButton addBTN = new JButton("ȷ������");
	private JButton canBTN = new JButton("ȡ������");

	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();
	private JPanel p5 = new JPanel();
	private Container container = this.getContentPane();

	private ImageIcon icon = new ImageIcon(".//icons//flower.jpg");

	public AddDialog(JFrame f) {
		super(f, "������Ʒ", true);
		

		p1.setLayout(new FlowLayout());
		p1.add(nameLabel);
		p1.add(nameText);
		p2.setLayout(new FlowLayout());
		p2.add(numLabel);
		p2.add(numText);
		p3.setLayout(new FlowLayout());
		p3.add(sumLabel);
		p3.add(sumText);
		p4.setLayout(new FlowLayout());
		p4.add(priceLabel);
		p4.add(priceText);
		p5.setLayout(new FlowLayout());
		p5.add(addBTN);
		p5.add(canBTN);

		container.setLayout(new GridLayout(5, 1));
		container.add(p1);
		container.add(p2);
		container.add(p3);
		container.add(p4);
		container.add(p5);

		BtnClickListener btnClick = new BtnClickListener();
		addBTN.addActionListener(btnClick);
		canBTN.addActionListener(btnClick);

		this.setSize(200, 250);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	private String result;

	public class BtnClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ManageGoods mg = new ManageGoods();
			String btnname = e.getActionCommand();
			switch (btnname) {
			case "ȷ������":
				try {
					if (nameText.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "��Ʒ���Ʋ���Ϊ�գ�����");
						break;
					}
					if (numText.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "��Ʒ��Ų���Ϊ�գ�����");
						break;
					}
					if (sumText.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "��Ʒ��������Ϊ�գ�����");
						break;
					}
					if (priceText.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "��Ʒ���۲���Ϊ�գ�����");
						break;
					}
					Goods good = new Goods(nameText.getText(), numText.getText(), sumText.getText(),
							priceText.getText());
					int i = mg.addGood(good);
					if (i == 0) {
						result = "��������Ʒ" + "\n��Ʒ���ƣ�" + good.getName() + "\n��Ʒ��ţ�" + good.getNum() + "\n��Ʒ������"
								+ good.getSum() + "\n��Ʒ���ۣ�" + good.getPrice();
						dispose();
					}
					if (i == 1) {
						JOptionPane.showMessageDialog(null, "�Ѵ��������Ʒ���ƣ�����");
						result = "����ʧ�ܣ�����";
					}
					if (i == 2) {
						JOptionPane.showMessageDialog(null, "�Ѵ��������Ʒ��ţ�����");
						result = "����ʧ�ܣ�����";
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "�����������Ʒ������");
					result = "����ʧ�ܣ�����";
				}
				break;
			case "ȡ������":
				dispose();
				break;
			default:
				break;
			}
		}
	}

	public String getResult() {
		return result;
	}
}
