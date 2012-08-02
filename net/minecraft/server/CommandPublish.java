package net.minecraft.server;

public class CommandPublish extends CommandAbstract {

    public CommandPublish() {}

    public String b() {
        return "publish";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        String s = MinecraftServer.getServer().a(EnumGamemode.SURVIVAL, false);

        if (s != null) {
            a(icommandlistener, "commands.publish.started", new Object[] { s});
        } else {
            a(icommandlistener, "commands.publish.failed", new Object[0]);
        }
    }
}
