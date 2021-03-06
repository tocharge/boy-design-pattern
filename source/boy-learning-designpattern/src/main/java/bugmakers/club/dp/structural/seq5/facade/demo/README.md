3.外观模式应用实例

下面通过一个应用实例来进一步学习和理解外观模式。  

3.1 实例说明

某软件公司欲开发一个可应用于多个软件的文件加密模块，该模块可以对文件中的数据进行加密并将加密之后的数据存储在一个新文件中，具体的流程包括三个部分，分别是读取源文件、加密、保存加密之后的文件，其中，读取文件和保存文件使用流来实现，加密操作通过求模运算实现。这三个操作相对独立，为了实现代码的独立重用，让设计更符合单一职责原则，这三个操作的业务代码封装在三个不同的类中。  

现使用外观模式设计该文件加密模块。

3.2 实例类图

通过分析，本实例结构图如图4所示。  
![图4 文件加密模块结构图](https://upload-images.jianshu.io/upload_images/5792176-f1c38344eb8efb17.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)  
图4 文件加密模块结构图  

在图4中，EncryptFacade充当外观类，FileReader、CipherMachine和FileWriter充当子系统类。  

3.3 实例代码

(1) FileReader：文件读取类，充当子系统类。  
```java
class FileReader  
{  
    public string Read(string fileNameSrc)   
    {  
        System.out.println("读取文件，获取明文：");  
        FileStream fs = null;  
        StringBuilder sb = new StringBuilder();  
        try  
        {  
            fs = new FileStream(fileNameSrc, FileMode.Open);  
            int data;  
            while((data = fs.ReadByte())!= -1)   
            {  
                sb = sb.Append((char)data);  
            }  
            fs.Close();  
            System.out.println(sb.ToString());  
        }  
        catch(FileNotFoundException e)   
        {  
            System.out.println("文件不存在！");  
        }  
        catch(IOException e)   
        {  
            System.out.println("文件操作错误！");  
        }  
        return sb.ToString();  
    }  
}  
```

(2) CipherMachine：数据加密类，充当子系统类。
```java
class CipherMachine  
{  
    public string Encrypt(string plainText)   
    {  
        System.out.println("数据加密，将明文转换为密文：");  
        string es = "";  
        char[] chars = plainText.ToCharArray();  
        foreach(char ch in chars)   
        {  
            string c = (ch % 7).ToString();  
            es += c;  
        }  
        System.out.println(es);  
        return es;  
    }  
    
}  
```

(3) FileWriter：文件保存类，充当子系统类。  
```java
class FileWriter  
{  
    public void Write(string encryptStr,string fileNameDes)   
    {  
        System.out.println("保存密文，写入文件。");  
        FileStream fs = null;  
        try  
        {  
            fs = new FileStream(fileNameDes, FileMode.Create);  
            byte[] str = Encoding.Default.GetBytes(encryptStr);  
            fs.Write(str,0,str.Length);  
            fs.Flush();  
            fs.Close();  
        }      
        catch(FileNotFoundException e)   
        {  
            System.out.println("文件不存在！");  
        }  
        catch(IOException e)   
        {  
            System.out.println(e.Message);  
            System.out.println("文件操作错误！");  
        }          
    }  
} 
```

(4) EncryptFacade：加密外观类，充当外观类。  
```java
class EncryptFacade  
{  
    //维持对其他对象的引用  
    private FileReader reader;  
    private CipherMachine cipher;  
    private FileWriter writer;  
    
    public EncryptFacade()  
    {  
        reader = new FileReader();  
        cipher = new CipherMachine();  
        writer = new FileWriter();  
    }  
    
    //调用其他对象的业务方法  
    public void FileEncrypt(string fileNameSrc, string fileNameDes)  
    {  
        string plainStr = reader.Read(fileNameSrc);  
        string encryptStr = cipher.Encrypt(plainStr);  
        writer.Write(encryptStr, fileNameDes);  
    }  
}  
```

(5) Program：客户端测试类  
```java
class Client  
{  
    public static void Main(string[] args)  
    {  
        EncryptFacade ef = new EncryptFacade();  
        ef.FileEncrypt("src.txt", "des.txt");  
        Console.Read();  
    }  
}  
```

编译并运行程序，输出结果如下：  
```
读取文件，获取明文：
bruce
数据加密，将明文转换为密文：
02513
保存密文，写入文件。
```

在本实例中，对文件src.txt中的数据进行加密，该文件内容为“Hello world!”，加密之后将密文保存到另一个文件des.txt中，程序运行后保存在文件中的密文为“233364062325”。在加密类CipherMachine中，采用求模运算对明文进行加密，将明文中的每一个字符除以一个整数（本例中为7，可以由用户来进行设置）后取余数作为密文。