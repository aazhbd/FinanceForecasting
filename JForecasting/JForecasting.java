import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MovingAverage extends JFrame{
	private JTextField t[];
	private JLabel l[];
	private JButton b;
	private JPanel p;
	private double result;
	
	public MovingAverage(){
		super("MovingAverage");
		
		int i, j;
		
		t=new JTextField[4];
		
		l=new JLabel[4];
		
		b=new JButton("Click to calculate");
		
		p=new JPanel();
		p.setLayout(new FlowLayout());
		
		for(i=0; i<4; i++){
			
			j=i+1;
			
			l[i]=new JLabel("Week no. " +j);
			
			t[i]=new JTextField(10);
		}
		
		t[3].setEnabled(false);
		
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(t[0].getText().equals("") && t[1].getText().equals("") && t[2].getText().equals("")){
					JOptionPane.showMessageDialog(null, "Enter Values in the field");
				}
				else{
					result=(Integer.parseInt(t[0].getText())+Integer.parseInt(t[1].getText())+Integer.parseInt(t[2].getText()))/3;
					t[3].setText(""+result);
				}
			}
		});
		
		for(i=0; i<4; i++){
			
			p.add(l[i]);
			
			p.add(t[i]);
		}
		
		p.add(b);
		add(p);
		
		setSize(250, 200);
		setVisible(true);
	}
}

class TrendTechnique extends JFrame{
	private JTextField t[], res;
	private JLabel l[];
	private JButton bt;
	private JPanel p;
	private int n, i, j;
	private double result, a, b, x, st, sy, sty, stq;
	
	public TrendTechnique(){
		
		super("Techniques of Trend");
		
		n=Integer.parseInt(JOptionPane.showInputDialog("Enter the number of Weeks:"));
		
		t=new JTextField[n];
		
		l=new JLabel[n];
		
		bt=new JButton("Click to calculate");
		
		p=new JPanel();
		p.setLayout(new FlowLayout());
		
		for(i=0; i<n; i++){
			
			j=i+1;
			
			l[i]=new JLabel("Week no. " +j+"  :");
			
			t[i]=new JTextField("0.00", 10);
		}
		
		res=new JTextField(20);
		res.setEnabled(false);
		
		bt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				for(i=1; i<=n; i++) st+=i;
				
				for(i=1; i<=n; i++) stq+=i*i;
				
				for(i=0; i<n; i++) sy+=Double.parseDouble(t[i].getText());
				
				for(i=0; i<n; i++){
					j=i+1;
					sty+=j*Double.parseDouble(t[i].getText());
				}
				
				if(sy>0){
					x=Integer.parseInt(JOptionPane.showInputDialog("Enter the number of week should be forcasted:"));
					b=((n*sty)-(st*sy))/((n*stq)-(st*st));
					a=(sy-(b*st))/n;
					result=(b*x)+a;
					res.setText(""+result);
				}
				else JOptionPane.showMessageDialog(null, "Enter Values in the fields");
			}
		});
		
		for(i=0; i<n; i++){
			
			p.add(l[i]);
			
			p.add(t[i]);
		}
		
		p.add(bt);
		p.add(res);
		
		add(p);
		
		setSize(280, 65+(50*n-1));
		setVisible(true);
	}
}

class WeightedMovingAverage extends JFrame{
	private JTextField t[], w[], res;
	private JLabel l[];
	private JButton b;
	private JPanel p;
	private int n, i, j;
	private double result;
	private Boolean f;
	
	public WeightedMovingAverage(){
		super("WeightedMovingAverage");
		
		n=Integer.parseInt(JOptionPane.showInputDialog("Enter the number of Inputs:"));
		
		t=new JTextField[n];
		
		w=new JTextField[n];
		
		l=new JLabel[n];

		b=new JButton("_____Click to Calculate______");
		
		p=new JPanel();
		p.setLayout(new FlowLayout());
		
		for(i=0; i<n; i++){
			
			j=i+1;
			
			l[i]=new JLabel("Week no. " +j);
			
			t[i]=new JTextField(10);
			
			w[i]=new JTextField(10);
		}
		
		res=new JTextField(20);
		res.setEnabled(false);
		
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				result=0;
				f=true;
				for(i=0; i<n; i++){
					if(t[i].getText().equals("") || w[i].getText().equals("")) f=false;
				}
				
				if(f){
					for(i=0; i<n; i++){
						result=(Integer.parseInt(t[i].getText())*Double.parseDouble(w[i].getText())+result);
					}
					res.setText(""+result);
				}
				else{
					JOptionPane.showMessageDialog(null, "Enter Values");
				}
			}
		});
		
		for(i=0; i<n; i++){
			
			p.add(l[i]);
			
			p.add(t[i]);
			
			p.add(w[i]);
		}
				
		p.add(b);
		
		p.add(res);
		
		add(p);
		
		setSize(350, 40*n);
		setVisible(true);
	}
}

class ExpoSmoothing extends JFrame{
	private JTextField t[];
	private JLabel l[];
	private JButton b;
	private JPanel p;
	private double result, s;
	private int i, j;
	private Boolean f;
	
	public ExpoSmoothing(){
		super("Exponential Smoothing");
		int n;
		n=Integer.parseInt(JOptionPane.showInputDialog("Enter the number of period: "));
		
		t=new JTextField[4];
		
		l=new JLabel[4];

		b=new JButton("Click to calculate");
		
		p=new JPanel();
		
		p.setLayout(new FlowLayout());
		
		l[0]=new JLabel("Forecast for previous period");
		
		t[0]=new JTextField(10);
		
		l[1]=new JLabel("Smoothing Constant");
		
		t[1]=new JTextField(10);
		
		l[2]=new JLabel("Actual Demand in last period");
		
		t[2]=new JTextField(10);
		
		l[3]=new JLabel("Forecast for period "+n);
		
		t[3]=new JTextField(10);
		
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				f=true;
				result=0;
				for(i=0; i<3; i++){
					if(t[i].getText().equals("")) f=false;
				}
				if(f){
					j=Integer.parseInt(t[0].getText());
					s=Double.parseDouble(t[1].getText());
					result=j+s*(Integer.parseInt(t[2].getText())-j);
					t[3].setText(""+result);
				}
				else JOptionPane.showMessageDialog(null, "Enter values");
			}
		});
		
		for(i=0; i<4; i++){
			
			p.add(l[i]);
			
			p.add(t[i]);
		}
		
		p.add(b);
		add(p);
		
		setSize(200, 350);
		setVisible(true);
	}
}


public class JForecasting extends JFrame{
	private JLabel l;
	private JButton ma, wma, es, tt, credit;
	private JPanel p;
	
	public JForecasting(){
		super("Forecasting Methods");
		l=new JLabel("Choose appropiate method to calculate Forecast");
		
		ma=new JButton("______Moving Average_______");
		
		wma=new JButton("___Weighted Moving Average__");
		
		es=new JButton("____Exponential Smoothing___");
		
		tt=new JButton("______Techniques of Trend___");
		
		credit=new JButton("___________Credits_________");
		
		p=new JPanel();
		p.setLayout(new FlowLayout());
		
		ma.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				MovingAverage oma=new MovingAverage();
				
			}
		});
		
		wma.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				WeightedMovingAverage owma=new WeightedMovingAverage();
				
			}
		});
		
		es.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				ExpoSmoothing oes=new ExpoSmoothing();
				
			}
		});
		
		tt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				TrendTechnique ott=new TrendTechnique();
				
			}
		});
		
		credit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null, "Software for Demand Forecasting,\n\n\nby\nAbdullah Al Zakir Hossain\nRegistration No. 04101009\nemail: aazhbd@hotmail.com");
			}
		});
		
		add(l, BorderLayout.NORTH);
		
		p.add(ma);
		p.add(wma);
		p.add(es);
		p.add(tt);
		p.add(credit);
		
		add(p);
		
		setSize(350, 220);
		setVisible(true);
	}
	
	public static void main(String args[]){
		
		JForecasting f=new JForecasting();
		
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				
				System.exit(0);
				
			}
		});
	}
}