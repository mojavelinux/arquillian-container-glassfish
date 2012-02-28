package org.jboss.arquillian.container.glassfish;

import org.jboss.arquillian.container.glassfish.clientutils.GlassFishClient;
import org.jboss.arquillian.container.spi.ConfigurationException;
import org.jboss.arquillian.container.spi.client.container.ContainerConfiguration;
import org.jboss.arquillian.container.spi.client.deployment.Validate;

public class CommonGlassFishConfiguration implements ContainerConfiguration
{

   /**
     * Glassfish Admin Server (DAS) host address.
     * Used to build the URL for the REST request.
     */
   protected String adminHost = "localhost";
   /**
     * Glassfish Admin Console port.
     * Used to build the URL for the REST request.
     */
   protected int adminPort = 4848;
   /**
     * Flag indicating the administration url uses a secure connection.
     * Used to build the URL for the REST request.
     */
   protected boolean adminHttps = false;
   /**
     * Flag indicating the remote server requires an admin user and password. 
     */
   private boolean authorisation = false;
   /**
     * Authorised admin user in the remote glassfish admin realm
     */
   private String adminUser;
   /**
     * Authorised admin user password
     */
   private String adminPassword;
   /**
     * Specifies the target to which you are  deploying. 
     * 
     * Valid values are:
     * 	server
     *   	Deploys the component to the default Admin Server instance.
     *   	This is the default value.
     *   instance_name
     *   	Deploys the component to  a  particular  stand-alone
     *   	sever instance.
     *   cluster_name
     *   	Deploys the component to every  server  instance  in
     *   	the cluster. (Though Arquillion use only one instance
     *   	to run the test case.)
     * 
     * The domain name as a target is not a reasonable deployment 
     * senarion in case of testing. 
     */
   private String target = GlassFishClient.ADMINSERVER;

   public CommonGlassFishConfiguration()
   {
      super();
   }

   public String getAdminHost()
   {
    	return adminHost;
    }

   public void setAdminHost(String adminHost)
   {
    	this.adminHost = adminHost;
    }

   public int getAdminPort()
   {
    	return adminPort;
    }

   public void setAdminPort(int adminPort)
   {
    	this.adminPort = adminPort;
    }

   public boolean isAdminHttps()
   {
    	return adminHttps;
    }

   public void setAdminHttps(boolean adminHttps)
   {
    	this.adminHttps = adminHttps;
    }

   public boolean isAuthorisation()
   {
        return authorisation;
    }

   public void setAuthorisation(boolean authorisation)
   {
        this.authorisation = authorisation;
    }

   public String getAdminUser()
   {
        return adminUser;
    }

   public void setAdminUser(String adminUser)
   {
        this.setAuthorisation(true);
        this.adminUser = adminUser;
    }

   public String getAdminPassword()
   {
        return adminPassword;
    }

   public void setAdminPassword(String adminPassword)
   {
        this.adminPassword = adminPassword;
    }

   public String getTarget()
   {
      return target; 
   }
   
   public void setTarget(String target)
   {
      this.target = target; 
   }
   
   /**
     * Validates if current configuration is valid, that is if all required
     * properties are set and have correct values
     */
   public void validate() throws ConfigurationException
   {
    	if(isAuthorisation())
    	{
    		Validate.notNull(getAdminUser(), "adminUser must be specified to use authorisation");
    		Validate.notNull(getAdminPassword(), "adminPassword must be specified to use authorisation");
    	}
    }

}