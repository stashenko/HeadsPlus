package io.github.thatsmusic99.headsplus.commands.maincommand.lists.blacklist;

import io.github.thatsmusic99.headsplus.commands.CommandInfo;
import io.github.thatsmusic99.headsplus.commands.maincommand.lists.AbstractListList;
import io.github.thatsmusic99.headsplus.locale.LocaleManager;

import java.util.List;

@CommandInfo(
		commandname = "blacklistwl",
		permission = "headsplus.maincommand.blacklistw.list",
		subcommand = "Blacklistwl",
		maincommand = true,
		usage = "/hp blacklistwl [Page No.]"
)
public class BlacklistwList extends AbstractListList {

    // M

	@Override
	public String getCmdDescription() {
		return LocaleManager.getLocale().descBlacklistwList();
	}

    @Override
    public String getExtendedType() {
        return "blacklist";
    }

    @Override
    public List<String> getList() {
        return config.getWorldBlacklist().list;
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public String getListType() {
        return "bl";
    }

    @Override
    public String getType() {
        return "world";
    }
}
