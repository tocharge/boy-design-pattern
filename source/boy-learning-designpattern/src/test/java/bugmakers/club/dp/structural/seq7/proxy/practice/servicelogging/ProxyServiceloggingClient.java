package bugmakers.club.dp.structural.seq7.proxy.practice.servicelogging;

/**
 * @Description:
 * @Author: Bruce
 * @Datetime: 2018/3/15 18:18
 */
public class ProxyServiceloggingClient {

    /**
     * 程序启动入口
     */
    public static void main(String[] args) {
        ServiceProxy proxy = new ServiceProxy();
        proxy.biz();
    }
}
