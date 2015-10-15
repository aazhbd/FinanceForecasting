import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MovingAverage extends Frame{
	private TextField t[];
	private Label l[];
	private Button b;
	private Panel p;
	private double result;
	
	public MovingAverage(){
		super("MovingAverage");
		
		int i, j;
		
		t=new TextField[4];
		
		l=new Label[4];

		b=new Button("Click to calculate");
		
		p=new Panel();
		p.setLayout(new FlowLayout());
		
		for(i=0; i<4; i++){
			
			j=i+1;
			
			l[i]=new Label("Week no. " +j);
			
			t[i]=new TextField(10);
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
		
		setSize(200, 200);
		setVisible(true);
	}
}

class TrendTechnique extends Frame{
	private TextField t[], res;
	private Label l[];
	private Button bt;
	private Panel p;
	private int n, i, j;
	private double result, a, b, x, st, sy, sty, stq;
	
	public TrendTechnique(){
		super("Techniques of Trend");
		
		n=Integer.parseInt(JOptionPane.showInputDialog("Enter the number of Weeks:"));
		
		t=new TextField[n];
		
		l=new Label[n];
		
		bt=new Button("Click to calculate");
		
		p=new Panel();
		p.setLayout(new FlowLayout());
		
		for(i=0; i<n; i++){
			
			j=i+1;
			
			l[i]=new Label("Week no. " +j+"  :");
			
			t[i]=new TextField("0.00", 10);
		}
		
		res=new TextField(20);
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

class WeightedMovingAverage extends Frame{
	private TextField t[], w[], res;
	private Label l[];
	private Button b;
	private Panel p;
	private int n, i, j;
	private double result;
	private Boolean f;
	
	public WeightedMovingAverage(){
		super("WeightedMovingAverage");
		
		n=Integer.parseInt(JOptionPane.showInputDialog("Enter the number of Inputs:"));
		
		t=new TextField[n];
		
		w=new TextField[n];
		
		l=new Label[n];

		b=new Button("_____Click to Calculate______");
		
		p=new Panel();
		p.setLayout(new FlowLayout());
		
		for(i=0; i<n; i++){
			
			j=i+1;
			
			l[i]=new Label("Week no. " +j);
			
			t[i]=new TextField(10);
			
			w[i]=new TextField(10);
		}
		
		res=new TextField(20);
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
		
		setSize(300, 50*n);
		setVisible(true);
	}
}

class ExpoSmoothing extends Frame{
	private TextField t[];
	private Label l[];
	private Button b;
	private Panel p;
	private double result, s;
	private int i, j;
	private Boolean f;
	
	public ExpoSmoothing(){
		super("Exponential Smoothing");
		int n;
		n=Integer.parseInt(JOptionPane.showInputDialog("Enter the number of period: "));
		
		t=new TextField[4];
		
		l=new Label[4];

		b=new Button("Click to calculate");
		
		p=new Panel();
		p.setLayout(new FlowLayout());
		
		l[0]=new Label("Forecast for previous period");
		
		t[0]=new TextField(10);
		
		l[1]=new Label("Smoothing Constant");
		
		t[1]=new TextField(10);
		
		l[2]=new Label("Actual Demand in last period");
		
		t[2]=new TextField(10);
		
		l[3]=new Label("Forecast for period "+n);
		
		t[3]=new TextField(10);
		
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


public class Forecasting extends Frame{
	private Label l;
	private Button ma, wma, es, tt, credit;
	private Panel p;
	
	public Forecasting(){
		super("Forecasting Methods");
		l=new Label("Choose appropiate method to calculate Forecast");
		
		ma=new Button("_______Moving Average_______");
		
		wma=new Button("___Weighted Moving Average__");
		
		es=new Button("____Exponential Smoothing___");
		
		tt=new Button("______Techniques of Trend___");
		
		credit=new Button("___________Credits_________");
		
		p=new Panel();
		p.setLayout(new FlowLayout());
		
		ma.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				MovingAverage oma=new MovingAverage();
				
				oma.addWindowListener(new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						
						System.exit(0);
						
					}
				});
			}
		});
		
		wma.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				WeightedMovingAverage owma=new WeightedMovingAverage();
				
				owma.addWindowListener(new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						
						System.exit(0);
						
					}
				});
			}
		});
		
		es.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				ExpoSmoothing oes=new ExpoSmoothing();
				
				oes.addWindowListener(new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						
						System.exit(0);
						
					}
				});
			}
		});
		
		tt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				TrendTechnique ott=new TrendTechnique();
				
				ott.addWindowListener(new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						
						System.exit(0);
						
					}
				});
			}
		});
		
		credit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null, "Software for Demand Forecasting,\nby\nAbdullah Al Zakir Hossain\nRegistration No. 04101009\nemail: aazhbd@hotmail.com");
			}
		});
		
		add(l, BorderLayout.NORTH);
		
		p.add(ma);
		p.add(wma);
		p.add(es);
		p.add(tt);
		p.add(credit);
		
		add(p);
		
		setSize(350, 200);
		setVisible(true);
	}
	
	public static void main(String args[]){
		
		Forecasting f=new Forecasting();
		
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				
				System.exit(0);
				
			}
		});
	}
}