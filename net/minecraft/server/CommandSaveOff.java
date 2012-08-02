package net.minecraft.server;

public class CommandSaveOff extends CommandAbstract {

    public CommandSaveOff() {}

    public String b() {
        return "save-off";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        MinecraftServer minecraftserver = MinecraftServer.getServer();

        for (int i = 0; i < minecraftserver.worldServer.length; ++i) {
            if (minecraftserver.worldServer[i] != null) {
                WorldServer worldserver = minecraftserver.worldServer[i];

                worldserver.savingDisabled = true;
            }
        }

        a(icommandlistener, "commands.save.disabled", new Object[0]);
    }
}
