module com.camilo.spglisoft_rpc_client {
    requires javafx.controls;
    requires javafx.fxml;
    requires protobuf.java;
    requires javax.annotation.api;
    requires io.grpc;
    requires io.grpc.stub;
    requires io.grpc.protobuf;
    requires com.google.common;
    requires java.sql;

    opens com.camilo.spglisoft_rpc_client.controladores to javafx.fxml;
    opens com.camilo.spglisoft_rpc_client to javafx.fxml;
    exports com.camilo.spglisoft_rpc_client.controladores;
    exports com.camilo.spglisoft_rpc_client;
}