package com.eastern.jinxin.hbase.service.impl;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eastern.jinxin.hbase.entity.SingletonBean;

@SingletonBean
@Service("connectionMgr")
public class HBaseConnectionMgr {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(HBaseConnectionMgr.class);
	public Connection getConnection(){
		return EnumHBaseCon.INSTANCE.getInstance();
	}
/*	@PostConstruct
	public void init() throws IOException {
		this.conf = HBaseConfiguration.create();
		System.setProperty("hadoop.home.dir", "D:\\installer\\work\\hadoop\\");
		System.getProperties().setProperty("HADOOP_USER_NAME", "etl");
		System.getProperties().setProperty("HADOOP_GROUP_NAME", "etl");
		conf.set("hbase.zookeeper.quorum", "59.212.226.37");
		conf.set("hbase.zookeeper.property.clientPort", "2181");
		conf.set("zookeeper.znode.parent", "/hbase-unsecure");
		conf.set("zookeeper.session.timeout", "6000");
	}*/
	private enum EnumHBaseCon{
		INSTANCE;
		private Connection connection;
		EnumHBaseCon(){
			try {
				System.setProperty("hadoop.home.dir", "D:\\installer\\work\\hadoop\\");
				System.getProperties().setProperty("HADOOP_USER_NAME", "etl");
				System.getProperties().setProperty("HADOOP_GROUP_NAME", "etl");
				Configuration conf = HBaseConfiguration.create();
				connection = ConnectionFactory.createConnection(conf);
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug("创建HBase异常"+e);
			}
		}
		private Connection getInstance(){
			return connection;
		}
	}
}
