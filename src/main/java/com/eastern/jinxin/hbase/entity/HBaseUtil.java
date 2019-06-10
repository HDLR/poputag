 package com.eastern.jinxin.hbase.entity;
 
 import java.io.IOException;
 import java.util.Collection;
 import org.apache.hadoop.hbase.HColumnDescriptor;
 import org.apache.hadoop.hbase.HTableDescriptor;
 import org.apache.hadoop.hbase.TableName;
 import org.apache.hadoop.hbase.client.Admin;
 import org.apache.hadoop.hbase.client.BufferedMutator;
 import org.apache.hadoop.hbase.client.Connection;
 import org.apache.hadoop.hbase.client.Delete;
 import org.apache.hadoop.hbase.client.Put;
 import org.apache.hadoop.hbase.client.Result;
 import org.apache.hadoop.hbase.client.Table;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 public class HBaseUtil
 {
   private static final Logger logger = LoggerFactory.getLogger(HBaseUtil.class);
 
   public static final byte[] ZERO_64BIT_INIT_VALUE = { 0, 0, 0, 0, 0, 0, 0, 0 };
 
   public static final byte[] ONE_64BIT_INIT_VALUE = { 0, 0, 0, 0, 0, 0, 0, 1 };
 
   public static String getValueAsString(Result result, byte[] family, byte[] qualifier)
   {
     if (isAnValidResult(result)) {
       byte[] byteVal = result.getValue(family, qualifier);
       if (byteVal != null) {
         String val = new String(byteVal);
         return val;
       }
     }
 
     return null;
   }
 
   public static boolean isAnValidResult(Result result)
   {
     return (result != null) && (!result.isEmpty());
   }
 
   public static byte[] emptyStringBytesIfNull(String val)
   {
     return val != null ? val.getBytes() : "".getBytes();
   }
 
   public static Table createOrUpgradeTable(Connection connection, TableName tableName, Collection<byte[]> allColumnFamilies) throws IOException
   {
     Admin admin = connection.getAdmin();
     HTableDescriptor desc;
     if (!admin.tableExists(tableName))
       desc = new HTableDescriptor(tableName);
     else {
       desc = new HTableDescriptor(admin.getTableDescriptor(tableName));
     }
 
     for (byte[] columnFamily : allColumnFamilies) {
       if (!desc.hasFamily(columnFamily)) {
         HColumnDescriptor columnDescriptor = new HColumnDescriptor(columnFamily);
         columnDescriptor.setMaxVersions(1);
         desc.addFamily(columnDescriptor);
       }
 
     }
 
     if (!admin.tableExists(tableName)) {
       admin.createTable(desc);
       logger.info("\n ### now create new table: {} \n", tableName.getNameAsString());
     }
     else {
       admin.disableTable(tableName);
       admin.modifyTable(tableName, desc);
       admin.enableTable(tableName);
 
       logger.info("\n ###  {} table already exists, upgrade table!\n", tableName.getNameAsString());
     }
 
     Table table = connection.getTable(tableName);
     return table;
   }
 
   public static long getStatisticCounterFromHBase(Table table, String rowKey, byte[] columnFamily, byte[] columnQualifier)
   {
     return getStatisticCounterFromHBase(table, rowKey.getBytes(), columnFamily, columnQualifier);
   }
 
   public static long getStatisticCounterFromHBase(Table table, byte[] rowKey, byte[] columnFamily, byte[] columnQualifier)
   {
     try
     {
       long numVal = table.incrementColumnValue(rowKey, columnFamily, columnQualifier, 0L);
       return numVal;
     } catch (IOException e) {
       e.printStackTrace();
     }
 
     return 0L;
   }
 
   public static long increaseCounter(Table table, byte[] rowKey, byte[] columnFamily, byte[] columnQualifier, long amount)
     throws IOException
   {
     long postValue = table.incrementColumnValue(rowKey, columnFamily, columnQualifier, amount);
     return postValue;
   }
 
   public static void addColumnWithValue(Table table, String key, byte[] columnFamily, byte[] columnQualifier, String value)
     throws IOException
   {
     Put put = new Put(key.getBytes());
     put.addColumn(columnFamily, columnQualifier, value.getBytes());
     table.put(put);
   }
 
   public static void addColumnWithValueAsync(BufferedMutator bufferedMutator, String key, byte[] columnFamily, byte[] columnQualifier, String value)
     throws IOException
   {
     Put put = new Put(key.getBytes());
     put.addColumn(columnFamily, columnQualifier, value.getBytes());
     bufferedMutator.mutate(put);
   }
 
   public static void moveOrRenameAColumn(BufferedMutator bufferedMutator, byte[] rowKey, byte[] newValue, byte[] oldFaimily, byte[] oldColumn, byte[] newFaimily, byte[] newColumn)
     throws IOException
   {
     Delete del = new Delete(rowKey);
     del.addColumns(oldFaimily, oldColumn);
 
     bufferedMutator.mutate(del);
 
     Put putNew = new Put(rowKey);
     putNew.addColumn(newFaimily, newColumn, newValue);
     bufferedMutator.mutate(putNew);
   }
 }

/* Location:           C:\Users\root\Desktop\SeaBoxTag\WEB-INF\classes\
 * Qualified Name:     com.seabox.tagsys.usertags.hbase.HBaseUtil
 * JD-Core Version:    0.6.0
 */