package edu.emp.pfe.api;

import edu.emp.pfe.VirtualEnvSample2;
import edu.emp.pfe.instances.VagrantInstance;
import edu.emp.pfe.model.VirtualEnvironment;
import edu.emp.pfe.model.VirtualMachine;
import edu.emp.pfe.utilities.system.Output;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        VirtualMachine virtualMachine = new VirtualMachine("3", "chakib", "password");

        return virtualMachine.getVm_id();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void world(String test) {
        VirtualEnvironment virtualEnvironment = VirtualEnvSample2.virtualEnvironment;
        VagrantInstance vagrantInstance = new VagrantInstance(virtualEnvironment);
        vagrantInstance.generate( null);

        new Thread(() -> {
            Output start = vagrantInstance.start(null, line -> {
                System.out.println(line);

                URL url = null;
                try {
                    url = new URL("http://localhost:8080/cyber_range_api/hello-world/post_test");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "text/plain");
                    con.setDoOutput(true);

                    byte[] out = line.getBytes(StandardCharsets.UTF_8);
                    int length = out.length;
                    con.setFixedLengthStreamingMode(length);

                    con.connect();

                    OutputStream os = con.getOutputStream();
                    os.write(out);

                    os.close();
                    con.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }).start();
    }

    @POST
    @Path("/post_test")
    @Consumes(MediaType.TEXT_PLAIN)
    public void postTest(String test) {
        System.out.println(test);
    }
}