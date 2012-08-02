package net.minecraft.server;

public class CommandSaveAll extends CommandAbstract {

    public CommandSaveAll() {}

    public String b() {
        return "save-all";
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        MinecraftServer minecraftserver = MinecraftServer.getServer();

        icommandlistener.sendMessage(icommandlistener.a("commands.save.start", new Object[0]));
        if (minecraftserver.getServerConfigurationManager() != null) {
            minecraftserver.getServerConfigurationManager().savePlayers();
        }

        try {
            for (int i = 0; i < minecraftserver.worldServer.length; ++i) {
                if (minecraftserver.worldServer[i] != null) {
                    WorldServer worldserver = minecraftserver.worldServer[i];
                    boolean flag = worldserver.savingDisabled;

                    worldserver.savingDisabled = false;
                    worldserver.save(true, (IProgressUpdate) null);
                    worldserver.savingDisabled = flag;
                }
            }
        } catch (ExceptionWorldConflict exceptionworldconflict) {
            a(icommandlistener, "commands.save.failed", new Object[] { exceptionworldconflict.getMessage()});
            return;
        }

        a(icommandlistener, "commands.save.success", new Object[0]);
    }
}
