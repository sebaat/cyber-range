package edu.emp.pfe;

import com.google.gson.Gson;
import edu.emp.pfe.model.VirtualEnvironment;

import java.util.HashMap;
import java.util.Map;

public class VirtualEnvTestSenario {

    static public VirtualEnvironment virtualEnvironment;
    static public String jsonDescription = "{\n" +
            "  \"virtualMachines\": [\n" +
            "    {\n" +
            "      \"vm_id\": \"kali\",\n" +
            "      \"boxName\": \"kaliNoGUI\",\n" +
            "      \"password\": \"kali\",\n" +
            "      \"networks\": [\n" +
            "        {\n" +
            "          \"networkType\": \"privet_network\",\n" +
            "          \"ipAssignment\": \"static_ip\",\n" +
            "          \"ipAddress\": \"10.0.0.5\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"dockerProvisioners\": [],\n" +
            "      \"scriptProvisioners\": [\n" +
            "        {\n" +
            "          \"script\": \"#!/bin/bash\\r\\n    \\r\\n    # some command\\r\\n            \"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"ansibleProvisioners\": []\n" +
            "    },\n" +
            "    {\n" +
            "      \"vm_id\": \"database\",\n" +
            "      \"boxName\": \"boxWithDocker\",\n" +
            "      \"password\": \"database\",\n" +
            "      \"networks\": [\n" +
            "        {\n" +
            "          \"networkType\": \"privet_network\",\n" +
            "          \"ipAssignment\": \"static_ip\",\n" +
            "          \"ipAddress\": \"10.0.0.7\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"dockerProvisioners\": [\n" +
            "        {\n" +
            "          \"imageName\": \"mysql\",\n" +
            "          \"containerName\": \"some_mysql\",\n" +
            "          \"cmd\": \"\",\n" +
            "          \"args\": \"--rm -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=testDB\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"scriptProvisioners\": [\n" +
            "        {\n" +
            "          \"script\": \"#!/bin/bash\\r\\n    \\r\\n    # some command\\r\\n            \"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"ansibleProvisioners\": []\n" +
            "    },\n" +
            "    {\n" +
            "      \"vm_id\": \"webServer\",\n" +
            "      \"boxName\": \"webServer\",\n" +
            "      \"password\": \"webServer\",\n" +
            "      \"networks\": [\n" +
            "        {\n" +
            "          \"networkType\": \"privet_network\",\n" +
            "          \"ipAssignment\": \"static_ip\",\n" +
            "          \"ipAddress\": \"10.0.0.6\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"dockerProvisioners\": [],\n" +
            "      \"scriptProvisioners\": [\n" +
            "        {\n" +
            "          \"script\": \"# installation\\r\\n\\r\\napt-get -y update\\r\\n\\r\\npip3 install django\\r\\n\\r\\npip3 install pymysql\\r\\n\\r\\napt-get -y install mysql-client\\r\\n\\r\\n# web application lunching\\r\\n\\r\\ncd /vagrant/for_django_3-0\\r\\n\\r\\npython3 manage.py makemigrations\\r\\n\\r\\npython3 manage.py migrate\\r\\n\\r\\npython3 manage.py runserver 0.0.0.0:80\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"ansibleProvisioners\": []\n" +
            "    }\n" +
            "  ],\n" +
            "  \"vagrantEnvPath\": \"/home/pfe21/Documents/cyber_range/vagrant_environments/prof_1/exo1/instance-1\",\n" +
            "  \"dockerImagesPaths\": {\n" +
            "    \"vulnerables/web-dvwa\": \"/home/pfe21/Documents/cyber_range/vagrant_environments/prof_1/images/docker/dockerimage\",\n" +
            "    \"mysql\": \"/home/pfe21/Documents/cyber_range/vagrant_environments/prof_1/images/docker/mysqlDockerImage\"\n" +
            "  },\n" +
            "  \"vagrantBoxesPaths\": {},\n" +
            "  \"uploadPath\": \"/home/pfe21/Documents/cyber_range/vagrant_environments/prof_1/images/upload\"\n" +
            "}";

    static public String jsonDescription2 = "{\n" +
            "  \"virtualMachines\": [\n" +
            "    {\n" +
            "      \"vm_id\": \"database\",\n" +
            "      \"boxName\": \"boxWithDocker\",\n" +
            "      \"password\": \"database\",\n" +
            "      \"networks\": [\n" +
            "        {\n" +
            "          \"networkType\": \"privet_network\",\n" +
            "          \"ipAssignment\": \"static_ip\",\n" +
            "          \"ipAddress\": \"10.0.0.7\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"dockerProvisioners\": [\n" +
            "        {\n" +
            "          \"imageName\": \"mysql\",\n" +
            "          \"containerName\": \"some_mysql\",\n" +
            "          \"cmd\": \"\",\n" +
            "          \"args\": \"--rm -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=testDB\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"scriptProvisioners\": [\n" +
            "        {\n" +
            "          \"script\": \"#!/bin/bash\\r\\n    \\r\\n    # some command\\r\\n            \"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"ansibleProvisioners\": []\n" +
            "    },\n" +
            "    {\n" +
            "      \"vm_id\": \"webServer\",\n" +
            "      \"boxName\": \"webServer\",\n" +
            "      \"password\": \"webServer\",\n" +
            "      \"networks\": [\n" +
            "        {\n" +
            "          \"networkType\": \"privet_network\",\n" +
            "          \"ipAssignment\": \"static_ip\",\n" +
            "          \"ipAddress\": \"10.0.0.6\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"dockerProvisioners\": [],\n" +
            "      \"scriptProvisioners\": [\n" +
            "        {\n" +
            "          \"script\": \"# installation\\r\\n\\r\\napt-get -y update\\r\\n\\r\\npip3 install django\\r\\n\\r\\npip3 install pymysql\\r\\n\\r\\napt-get -y install mysql-client\\r\\n\\r\\n# web application lunching\\r\\n\\r\\ncd /vagrant/for_django_3-0\\r\\n\\r\\npython3 manage.py makemigrations\\r\\n\\r\\npython3 manage.py migrate\\r\\n\\r\\npython3 manage.py runserver 0.0.0.0:80\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"ansibleProvisioners\": []\n" +
            "    }\n" +
            "  ],\n" +
            "  \"vagrantEnvPath\": \"/home/pfe21/Documents/cyber_range/vagrant_environments/prof_1/exo1/instance-1\",\n" +
            "  \"dockerImagesPaths\": {\n" +
            "    \"vulnerables/web-dvwa\": \"/home/pfe21/Documents/cyber_range/vagrant_environments/prof_1/images/docker/dockerimage\",\n" +
            "    \"mysql\": \"/home/pfe21/Documents/cyber_range/vagrant_environments/prof_1/images/docker/mysqlDockerImage\"\n" +
            "  },\n" +
            "  \"vagrantBoxesPaths\": {},\n" +
            "  \"uploadPath\": \"/home/pfe21/Documents/cyber_range/vagrant_environments/prof_1/images/upload\"\n" +
            "}";

    static {
        Gson gson = new Gson();
        virtualEnvironment = gson.fromJson(jsonDescription2, VirtualEnvironment.class);
    }

}

