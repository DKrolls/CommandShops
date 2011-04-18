package net.centerleft.localshops;

import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;

import com.nijiko.coelho.iConomy.iConomy;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class ShopsPluginListener extends ServerListener {
	private final LocalShops plugin;
	
	protected iConomy iConomy;
	protected Permissions permissions;
	protected PermissionHandler gmPermissionCheck; 
	protected boolean usePermissions = false;
	protected boolean useiConomy = false;
	
	public ShopsPluginListener(LocalShops instance) { 
		plugin = instance;
	}

	@Override
	public void onPluginEnable(PluginEnableEvent event) {
        if(event.getPlugin().getDescription().getName().equals("iConomy")) {
            iConomy = (iConomy)event.getPlugin();
            System.out.println("LocalShops: Attached to iConomy.");
            useiConomy = true;
    		ShopData.currencyName = iConomy.getBank().getCurrency();
        }
        
        if(event.getPlugin().getDescription().getName().equals("Permissions")) {
        	permissions = (Permissions)event.getPlugin();
            gmPermissionCheck = permissions.getHandler();
        	System.out.print("LocalShops: Attached to Permissions");
        	usePermissions = true;
            
            
        } 
    }
    
	@Override
    public void onPluginDisable(PluginDisableEvent event) {
    	if(event.getPlugin().getDescription().getName().equals("iConomy")) {
            iConomy = null;
            System.out.println("LocalShops: Lost connection to iConomy.");
            useiConomy = false;
    	}
        if(event.getPlugin().getDescription().getName().equals("Permissions")) {
        	permissions = (Permissions)event.getPlugin();
        	System.out.print("LocalShops: Lost connection to Permissions");
        	usePermissions = false;
        }
    }
}
