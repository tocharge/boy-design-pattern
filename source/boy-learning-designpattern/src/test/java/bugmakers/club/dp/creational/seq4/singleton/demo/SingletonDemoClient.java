package bugmakers.club.dp.creational.seq4.singleton.demo;

/**
 * @Description:
 * @Author: Bruce
 * @Datetime: 2018/3/6 17:33
 */
public class SingletonDemoClient {

    /**
     * 程序启动入口
     */
    public static void main(String[] args) {

        SingletonLoadBalancer lb1, lb2, lb3, lb4;

        lb1 = SingletonLoadBalancer.getLoadBalancer();
        lb2 = SingletonLoadBalancer.getLoadBalancer();
        lb3 = SingletonLoadBalancer.getLoadBalancer();
        lb4 = SingletonLoadBalancer.getLoadBalancer();

        // 判断服务器负载均衡器是否相同
        if(lb1 == lb2 && lb2 == lb3 && lb3 == lb4) {
            System.out.println("服务器负载均衡器具有唯一性！");
        }

        // 增加服务器
        lb1.addServer("Server 1");
        lb1.addServer("Server 2");
        lb1.addServer("Server 3");
        lb1.addServer("Server 4");

        //模拟客户端请求的分发
        for (int i = 0; i < 10; i++) {
            String server = lb1.getServer();
            System.out.println("分发请求至服务器： " + server);
        }
    }
}
