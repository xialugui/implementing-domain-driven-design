/**
 * 此包封装了限界上下文所有的接口，接口主要按协议区分，如：RESTful API、WebSocket、FTP和私有协议等。
 * 我们为每个协议建立对应的包：RESTful API->rest（{@link cn.xialugui.toolmanagement.interfaces.rest}）；
 * WebSocket->websocket（{@link cn.xialugui.toolmanagement.interfaces.websocket}）；事件处理器：
 * EventHandler->eventhandler（{@link cn.xialugui.toolmanagement.interfaces.websocket}）
 */
package cn.xialugui.toolmanagement.interfaces;