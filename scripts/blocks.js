Events.on(ContentInitEvent, e => {
     Vars.content.block("arikoth-dissolved-shellstone-wall").attributes.set(Attribute.get("shell"), 1);
     Vars.content.block("arikoth-dissolved-carapacyte-wall").attributes.set(Attribute.get("shells"), 1);

     Vars.content.block("arikoth-drill06").attribute = Attribute.get("shell");
     Vars.content.block("arikoth-drill07").attribute = Attribute.get("shells");
});