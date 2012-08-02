package net.minecraft.server;

import java.util.List;

public class CommandTime extends CommandAbstract {

    public CommandTime() {}

    public String b() {
        return "time";
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.time.usage", new Object[0]);
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        if (astring.length > 1) {
            int i;

            if (astring[0].equals("set")) {
                if (astring[1].equals("day")) {
                    i = 0;
                } else if (astring[1].equals("night")) {
                    i = 12500;
                } else {
                    i = a(icommandlistener, astring[1], 0);
                }

                this.a(icommandlistener, i);
                a(icommandlistener, "commands.time.set", new Object[] { Integer.valueOf(i)});
                return;
            }

            if (astring[0].equals("add")) {
                i = a(icommandlistener, astring[1], 0);
                this.b(icommandlistener, i);
                a(icommandlistener, "commands.time.added", new Object[] { Integer.valueOf(i)});
                return;
            }
        }

        throw new ExceptionUsage("commands.time.usage", new Object[0]);
    }

    public List a(ICommandListener icommandlistener, String[] astring) {
        return astring.length == 1 ? a(astring, new String[] { "set", "add"}) : (astring.length == 2 && astring[0].equals("set") ? a(astring, new String[] { "day", "night"}) : null);
    }

    protected void a(ICommandListener icommandlistener, int i) {
        for (int j = 0; j < MinecraftServer.getServer().worldServer.length; ++j) {
            MinecraftServer.getServer().worldServer[j].setTimeAndFixTicklists((long) i);
        }
    }

    protected void b(ICommandListener icommandlistener, int i) {
        for (int j = 0; j < MinecraftServer.getServer().worldServer.length; ++j) {
            WorldServer worldserver = MinecraftServer.getServer().worldServer[j];

            worldserver.setTimeAndFixTicklists(worldserver.getTime() + (long) i);
        }
    }
}
