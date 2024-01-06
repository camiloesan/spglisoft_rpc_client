package com.camilo.spglisoft_rpc_client.modelo;
import io.grpc.*;
import spglisoft.DataManagerGrpc;
import spglisoft.Spglisoft;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RpcClient {
    private static final Logger logger = Logger.getLogger(RpcClient.class.getName());

    private final DataManagerGrpc.DataManagerBlockingStub blockingStub;
    private final DataManagerGrpc.DataManagerStub asyncStub;
    public static final String TARGET = "192.168.1.145:50051";

    //could make getChannel a singleton
    public RpcClient(Channel channel) {
        blockingStub = DataManagerGrpc.newBlockingStub(channel);
        asyncStub = DataManagerGrpc.newStub(channel);
    }

    public Spglisoft.DeveloperCredentials getDeveloper(String matricula, String contrasena) {
        Spglisoft.DeveloperRequest request
                = Spglisoft.DeveloperRequest.newBuilder()
                .setMatricula(matricula)
                .setContrasena(contrasena)
                .build();
        Spglisoft.DeveloperCredentials response = null;
        try {
            response = blockingStub.getDeveloper(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
        return response;
    }

    public Spglisoft.ProjectManagerCredentials getProjectManager(String numeroPersonal, String contrasena) {
        Spglisoft.ProjectManagerRequest request
                = Spglisoft.ProjectManagerRequest.newBuilder()
                .setNumeroPersonal(numeroPersonal)
                .setContrasena(contrasena)
                .build();
        Spglisoft.ProjectManagerCredentials response = null;
        try {
            response = blockingStub.getProjectManager(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
        return response;
    }

    public Spglisoft.DevsByProjectIdList getDevsByProjectId(int projectId) {
        Spglisoft.DevsByProjectIdRequest request
                = Spglisoft.DevsByProjectIdRequest.newBuilder()
                .setIdProyecto(projectId)
                .build();
        Spglisoft.DevsByProjectIdList response = null;
        try {
            response = blockingStub.getDevsByProjectId(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
        return response;
    }

    public Spglisoft.DevsByProjectIdList getDevsWithoutProject() {
        Spglisoft.NoParams request
                = Spglisoft.NoParams.newBuilder()
                .build();
        Spglisoft.DevsByProjectIdList response = null;
        try {
            response = blockingStub.getDevsWithoutProject(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
        return response;
    }

    public Spglisoft.RowsAffected deleteDevFromProject(int idDeveloper) {
        Spglisoft.DeleteDevRequest request
                = Spglisoft.DeleteDevRequest.newBuilder()
                .setIdDesarrollador(idDeveloper)
                .build();
        Spglisoft.RowsAffected response = null;
        try {
            response = blockingStub.deleteDevFromProject(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
        return response;
    }

    public Spglisoft.RowsAffected addDevToProject(int idDeveloper, int idProject) {
        Spglisoft.DevProjIdRequest request
                = Spglisoft.DevProjIdRequest.newBuilder()
                .setIdDesarrollador(idDeveloper)
                .setIdProyecto(idProject)
                .build();
        Spglisoft.RowsAffected response = null;
        try {
            response = blockingStub.addDevToProject(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
        return response;
    }

    public Spglisoft.RowsAffected assignActivity(int idActivity , int idDeveloper) {
        Spglisoft.ActDevIdRequest request
                = Spglisoft.ActDevIdRequest.newBuilder()
                .setIdActividad(idActivity)
                .setIdDesarrollador(idDeveloper)
                .build();
        Spglisoft.RowsAffected response = null;
        try {
            response = blockingStub.assignActivity(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
        return response;
    }
}
