package bugmakers.club.dp.creational.seq1.simplefactory.demooptimized;

import bugmakers.club.dp.creational.seq1.simplefactory.demo.Chart;
import bugmakers.club.dp.creational.seq1.simplefactory.demo.ChartFactory;

/**
 * @Description: 简单工厂客户端实例优化客户端
 * @Author: Bruce
 * @Datetime: 2018/3/1 16:55
 */
public class SimpleFactoryDemoOptimizedClient {

    public static void main(String args[]) {
        Chart chart;

        //读取配置文件中的参数
        String type = SimpleFactoryXMLUtil.getChartType();

        //创建产品对象
        chart = ChartFactory.getChart(type);
        chart.display();
    }
}
