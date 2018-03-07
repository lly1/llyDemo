package dtu;

import java.util.Date;

public class Convert {
	public static InvertMsg ConvertIM(String s){
      InvertMsg invertMsg=new InvertMsg();
		float Temperature = (int) (Integer.parseInt(s.substring(18, 22), 16)*0.1); 
		float Vpv1=(int) (Integer.parseInt(s.substring(22, 26), 16)*0.1); 
		float Vpv2=(int) (Integer.parseInt(s.substring(26, 30), 16)*0.1); 
		float Ipv1=(int) (Integer.parseInt(s.substring(30, 34), 16)*0.1); 
		float Ipv2=(int) (Integer.parseInt(s.substring(34, 38), 16)*0.1); 
		float Iac=(int) (Integer.parseInt(s.substring(38, 42), 16)*0.1); 
		float Vac=(int) (Integer.parseInt(s.substring(42, 46), 16)*0.1); 
		float Fac=(int) (Integer.parseInt(s.substring(46, 50), 16)*0.01); 
		float Pac=(int) (Integer.parseInt(s.substring(50, 54), 16));
		float Mode=(int) (Integer.parseInt(s.substring(70, 74), 16));
		Integer ErrorMsg=(int) (Integer.parseInt(s.substring(74, 82), 16)*0.1);
		float E_total=(int) (Integer.parseInt(s.substring(54, 58), 16)*0.01);
		float H_total=(int) (Integer.parseInt(s.substring(62, 66), 16)*0.01);
		float E_today=(int) (Integer.parseInt(s.substring(82, 86), 16)*0.01);
		float E_todayA=(int) (Integer.parseInt(s.substring(86, 90), 16)*0.01);
		float E_todayB=(int) (Integer.parseInt(s.substring(90, 94), 16)*0.01);
		float Temperature2=(int) (Integer.parseInt(s.substring(94, 98), 16)*0.1);
		float Vpn=(int) (Integer.parseInt(s.substring(98, 102), 16)*0.1);
		Date receiveTime=new Date();
		invertMsg.setTemperature( String.valueOf(Temperature));
		invertMsg.setVpv1(String.valueOf(Vpv1));
		invertMsg.setVpv2(String.valueOf(Vpv2));
		invertMsg.setIpv1(String.valueOf(Ipv1));
		invertMsg.setIpv2(String.valueOf(Ipv2));
		invertMsg.setIac(String.valueOf(Iac));
		invertMsg.setVac(String.valueOf(Vac));
		invertMsg.setFac(String.valueOf(Fac));
		invertMsg.setPac(String.valueOf(Pac));
		invertMsg.setMode(String.valueOf(Mode));
		invertMsg.setErrorMsg(String.valueOf(ErrorMsg));
		invertMsg.setE_total(String.valueOf(E_today));
		invertMsg.setE_todayA(String.valueOf(E_todayA));
		invertMsg.setE_todayB(String.valueOf(E_todayB));
		invertMsg.setE_total(String.valueOf(E_total));
		invertMsg.setH_total(String.valueOf(H_total));
		invertMsg.setTemperature2(String.valueOf(Temperature2));
		invertMsg.setVPN(String.valueOf(Vpn));
		invertMsg.setReceiveTime(receiveTime);
		return invertMsg;
		
	}
	public static void main(String[] args) {
		InvertMsg invertMsg=new InvertMsg();
		invertMsg=ConvertIM("3a3a0011010011822e00640e1c000000140001002108fe138802f100010bd70000000000010000000002c9000002c9009a0eb4317e00000924");
		System.out.println(invertMsg.getReceiveTime());
	}
}
