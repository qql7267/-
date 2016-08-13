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

	private JLabel nameLabel = new JLabel("商品名称");
	private JLabel numLabel = new JLabel("商品编号");
	private JLabel sumLabel = new JLabel("商品总数");
	private JLabel priceLabel = new JLabel("商品单价");

	private JTextField nameText = new JTextField(10);
	private JTextField numText = new JTextField(10);
	private JTextField sumText = new JTextField(10);
	private JTextField priceText = new JTextField(10);

	private JButton addBTN = new JButton("确认增加");
	private JButton canBTN = new JButton("取消操作");

	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();
	private JPanel p5 = new JPanel();
	private Container container = this.getContentPane();

	private ImageIcon icon = new ImageIcon(".//icons//flower.jpg");

	public AddDialog(JFrame f) {
		super(f, "增加商品", true);
		

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
			case "确认增加":
				try {
					if (nameText.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "商品名称不可为空！！！");
						break;
					}
					if (numText.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "商品编号不可为空！！！");
						break;
					}
					if (sumText.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "商品总数不可为空！！！");
						break;
					}
					if (priceText.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "商品单价不可为空！！！");
						break;
					}
					Goods good = new Goods(nameText.getText(), numText.getText(), sumText.getText(),
							priceText.getText());
					int i = mg.addGood(good);
					if (i == 0) {
						result = "已增加商品" + "\n商品名称：" + good.getName() + "\n商品编号：" + good.getNum() + "\n商品数量："
								+ good.getSum() + "\n商品单价：" + good.getPrice();
						dispose();
					}
					if (i == 1) {
						JOptionPane.showMessageDialog(null, "已存在这个商品名称！！！");
						result = "增加失败！！！";
					}
					if (i == 2) {
						JOptionPane.showMessageDialog(null, "已存在这个商品编号！！！");
						result = "增加失败！！！";
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "不存在这个商品！！！");
					result = "增加失败！！！";
				}
				break;
			case "取消操作":
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
