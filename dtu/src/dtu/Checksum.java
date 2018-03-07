package dtu;

public class Checksum {
	private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', 
        '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	public static String makeChecksum(String data) {
		String s1 = "";
		String s2 = "";
		if (data == null || data.equals("")) {
			return "";
		}
		int total = 0;
		int len = data.length();
		int num = 0;
		while (num < len) {
			String s = data.substring(num, num + 2);
			s1 = s1 + s;
			s2 = s2 + s;
			total += Integer.parseInt(s, 16);
			num = num + 2;
		}
		/**
		 * ��256���������255����16���Ƶ�FF
		 */
		int mod = total;
		String hex = Integer.toHexString(mod);
		len = hex.length();
		// �������У��λ�ĳ��ȣ���0,�����õ�����λУ��
		switch (len) {
		case 1:
			hex = "000" + hex;
			break;

		case 2:
			hex = "00" + hex;
			break;
		case 3:
			hex = "0" + hex;
			break;
		default:
			break;
		}
		return s2 + hex;
	}
	  /**
     * �ַ���ת���ֽ���
     */
    public static byte[] decode(String src) {
        int m = 0, n = 0;
        int byteLen = src.length() / 2; // ÿ�����ַ�����һ���ֽ�
        byte[] ret = new byte[byteLen];
        for (int i = 0; i < byteLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            int intVal = Integer.decode("0x" + src.substring(i * 2, m) + src.substring(m, n));
            ret[i] = Byte.valueOf((byte)intVal);
        }
        return ret;
    }
    /**
     * ��������
     * byte[] to hex string
     * 
     * @param bytes
     * @return
     */
    public static String bytesToHexFun2(byte[] bytes) {
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        for(byte b : bytes) { // ����λ�������ת�������Կ�������һ�ı���
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }

        return new String(buf);
    }

	public static void main(String[] args) {
		String s = "3A3A010000ff110200 ".replace(" ", "");
		System.out.println(Checksum.makeChecksum(s));
		byte[] b=Checksum.decode(Checksum.makeChecksum(s));
		for(byte a :b){
		System.out.print(a);
		}
	}
}
