package net.minecraft.server;

import java.util.List;

public class CommandPardon extends CommandAbstract {

    public CommandPardon() {}

    public String b() {
        return "pardon";
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.unban.usage", new Object[0]);
    }

    public boolean b(ICommandListener icommandlistener) {
        return MinecraftServer.getServer().getServerConfigurationManager().getNameBans().isEnabled() && super.b(icommandlistener);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length == 1 && astring[0].length() > 0) {
            MinecraftServer.getServer().getServerConfigurationManager().getNameBans().remove(astring[0]);
            a(icommandlistener, "commands.unban.success", new Object[] { astring[0]});
        } else {
            throw new ExceptionUsage("commands.unban.usage", new Object[0]);
        }
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, MinecraftServer.getServer().getServerConfigurationManager().getNameBans().getEntries().keySet()) : null;
    }
}
