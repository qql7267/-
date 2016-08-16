package Surface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Manages.ManageGoods;

public class GUIshow extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel nameLabel = new JLabel("商品名称");
	private JTextField nameText = new JTextField(12);
	private JTextArea resultText = new JTextArea(7, 18);

	private JButton addBTN = new JButton("增加");
	private JButton updBTN = new JButton("修改");
	private JButton delBTN = new JButton("删除");
	private JButton selBTN = new JButton("查询");

	private Container container = this.getContentPane();
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();

	private JPanel add_btn = new JPanel();
	private JPanel upd_btn = new JPanel();
	private JPanel del_btn = new JPanel();
	private JPanel sel_btn = new JPanel();

	public GUIshow(String str) {
	}

	public GUIshow() {
		resultText.setBackground(Color.CYAN);
		resultText.setEditable(false);
		resultText.setLineWrap(true);
		// nameText.setForeground(Color.RED);

		container.setLayout(new BorderLayout());

		p1.setLayout(new FlowLayout());
		p1.add(nameLabel);
		p1.add(nameText);

		p2.setLayout(new FlowLayout());
		p2.add(resultText);

		p3.setLayout(new BorderLayout());
		p3.add(p1, BorderLayout.NORTH);
		p3.add(p2, BorderLayout.SOUTH);
		container.add(p3, BorderLayout.WEST);

		add_btn.setLayout(new FlowLayout());
		add_btn.add(addBTN);
		upd_btn.setLayout(new FlowLayout());
		upd_btn.add(updBTN);
		del_btn.setLayout(new FlowLayout());
		del_btn.add(delBTN);
		sel_btn.setLayout(new FlowLayout());
		sel_btn.add(selBTN);

		p4.setLayout(new GridLayout(4, 1));
		p4.add(add_btn);
		p4.add(upd_btn);
		p4.add(del_btn);
		p4.add(sel_btn);
		container.add(p4, BorderLayout.EAST);

		this.setTitle("货存管理系统");
		this.setSize(300, 200);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

		BtnClickListener btnClick = new BtnClickListener();
		addBTN.addActionListener(btnClick);
		updBTN.addActionListener(btnClick);
		delBTN.addActionListener(btnClick);
		selBTN.addActionListener(btnClick);

	}

	public class BtnClickListener implements ActionListener {
		GUIshow gs = new GUIshow("1");

		public void actionPerformed(ActionEvent e) {
			ManageGoods mg = new ManageGoods();
			String nameBtn = e.getActionCommand();
			switch (nameBtn) {
			case "增加":
				AddDialog ad = new AddDialog(gs);
				resultText.setText(ad.getResult());
				break;
			case "修改":
				UpdDialog ud = new UpdDialog(gs);
				resultText.setText(ud.getResult());
				break;
			case "删除":
				try {
					if (nameText.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "请先输入商品名称");
						break;
					}
					String result = mg.delGood(nameText.getText());
					if (result.equals("1")) {
						JOptionPane.showMessageDialog(null, "不存在这个商品！！！");
						resultText.setText("删除失败！！！");
						break;
					}
					resultText.setText(result);
				} catch (Exception el) {
					el.printStackTrace();
				}
				break;
			case "查询":
				try {
					if (nameText.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "请先输入商品名称");
						break;
					}
					String result = mg.selGood(nameText.getText());
					if (result.equals("1")) {
						JOptionPane.showMessageDialog(null, "不存在这个商品！！！");
						resultText.setText("查询失败！！！");
						break;
					}
					resultText.setText(result);
				} catch (Exception el) {
					el.printStackTrace();
				}
				break;
			default:
				break;
			}
		}
	}
}
