package aa.mydatas.elastic.Alearn;

/**
 * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high.html
 * <p>
 * Marshalling可译作编码、编组、数据打包等，是计算机科学中把一个对象的内存表示变换为适合存储或发送的数据格式的过程。
 * Java High Level REST Client works on top of the Java Low Level REST client,
 * expose API specific methods, that accept request objects as an argument and return response objects,
 * so that request marshalling and response un-marshalling is handled by the client itself.
 * <p>
 * Each API can be called synchronously or asynchronously.
 * The synchronous methods return a response object,
 * while the asynchronous methods, whose names end with the async suffix,
 * require a listener argument that is notified (on the thread pool managed by the low level client)
 * once a response or an error is received
 * <p>
 * The Java High Level REST Client depends on the Elasticsearch core project.
 * It accepts the same request arguments as the TransportClient and returns the same response objects
 */
public class A_HighLevelClient {
}
