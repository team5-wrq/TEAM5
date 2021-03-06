package serialPort;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;



public class SerialListener implements SerialPortEventListener {
	public SerialTool tool;
    public SerialPort serialPort;
    public SerialListener(SerialTool tool,SerialPort serialPort)
    {
    	this.tool=tool;
        this.serialPort=serialPort;
    }
    
    public void serialEvent(SerialPortEvent serialPortEvent) {
        
        switch (serialPortEvent.getEventType()) {

            case SerialPortEvent.BI: // 10 通讯中断
                System.out.println("与串口设备通讯中断");
                break;

            case SerialPortEvent.OE: // 7 溢位（溢出）错误

            case SerialPortEvent.FE: // 9 帧错误

            case SerialPortEvent.PE: // 8 奇偶校验错误

            case SerialPortEvent.CD: // 6 载波检测

            case SerialPortEvent.CTS: // 3 清除待发送数据

            case SerialPortEvent.DSR: // 4 待发送数据准备好了

            case SerialPortEvent.RI: // 5 振铃指示

            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 输出缓冲区已清空
                break;
            
            case SerialPortEvent.DATA_AVAILABLE: // 1 串口存在可用数据
                
                //System.out.println("found data");
                byte[] data = null;
                
                try {
                    if (serialPort == null) {
                        System.out.println("串口对象为空！监听失败");
                    }
                    else {
                        data = tool.readFromPort(serialPort);    //读取数据，存入字节数组
                        System.out.println(new String(data,"UTF-8"));
                        //System.out.println(new String(data));
                        //JOptionPane.showInputDialog(new String(data));
                        //String dataOriginal = new String(data);    //将字节数组数据转换位为保存了原始数据的字符串
                    }                        
                    
                } catch (Exception e) {
                    System.exit(0);
                }    
                
                break;

        }

    }

}
