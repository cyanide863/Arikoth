Events.on(ContentInitEvent, e => {
     Vars.content.block("arikoth-komatiite-icher").attributes.set(Attribute.get("icher"), 0.25);
     Vars.content.block("arikoth-icher-condenser").attribute = Attribute.get("icher");
});