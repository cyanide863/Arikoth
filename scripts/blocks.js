Events.on(ContentInitEvent, e => {
     Vars.content.block("arikoth-metallic-scrap-wall").attributes.set(Attribute.get("scraps"), 1);
     Vars.content.block("arikoth-metallic-scrap-wall-damaged").attributes.set(Attribute.get("scraps"), 1.2);
     Vars.content.block("arikoth-silicarbide-wall").attributes.set(Attribute.get("silica"), 1);
     Vars.content.block("arikoth-red-slate-wall").attributes.set(Attribute.get("silica"), 1.2);

     Vars.content.block("arikoth-scrap-crusher").attribute = Attribute.get("scraps");
     Vars.content.block("arikoth-silica-crusher").attribute = Attribute.get("silica");
});