__config() -> {
    'scope' -> 'global',
    'strict' -> true
};

global_bot_team_name = 'bots';
global_bot_team_prefix = format('r [BOT] ');

__on_start() -> (
    if (!(team_list() ~ global_bot_team_name),
        team_add(global_bot_team_name)
    );
    team_property(global_bot_team_name, 'color', 'gray');
    team_property(global_bot_team_name, 'prefix', global_bot_team_prefix);
    team_property(global_bot_team_name, 'collisionRule', 'never');
);

__on_player_connects(player) -> (
    if (query(player, 'player_type') == 'fake',
        team_add(global_bot_team_name, player)
    )
);

__on_player_disconnects(player, reason) -> (
    if (query(player, 'player_type') == 'fake',
        team_leave(player)
    )
);