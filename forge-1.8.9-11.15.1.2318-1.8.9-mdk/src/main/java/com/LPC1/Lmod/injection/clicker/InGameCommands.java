package com.LPC1.Lmod.injection.clicker;


import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.LPC1.Lmod.injection.clicker.AutoClicker.*;

public class InGameCommands implements ICommand {

    public static int ReachValue;
    private static boolean MAXSPEEDSET = false;
    private static boolean MINSPEEDSET = false;
    private static final Random r = new Random();

    @Override
    public String getCommandName() {
        return "mod";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "Change mod settings";
    }

    @Override
    public List<String> getCommandAliases() {
        List<String> Aliases = new ArrayList<String>();
        Aliases.add("mod");
        Aliases.add("clicker");
        Aliases.add("ac");
        return Aliases;
    }

    @Override
    public void processCommand(ICommandSender iCommandSender, String[] strings) throws CommandException {
        if (strings.length == 0) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(""));
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("mod (on/off)"));
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("mod maxcps (1-20)"));
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("mod mincps (1-20)"));
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("mod cps (on / off)"));
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(""));
        }
        if (strings[0].equalsIgnoreCase("on")) {
            if (MAXSPEEDSET && MINSPEEDSET) {
                ClickerON = true;
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("mod turned on"));
                GenerateSequence(MAXSpeed, MINSpeed, 20);
            } else {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("You need to set maxcps and mincps first"));
            }
        }
        if (strings[0].equalsIgnoreCase("off")) {
            MAXSPEEDSET = false;
            MINSPEEDSET = false;
            ClickerON = false;
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("mod turned off"));
        }
        if (strings[0].equalsIgnoreCase("cps") && strings[1].equalsIgnoreCase("off")) {
            CPSCMD = false;
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("CPS counter turned off"));
        }
        if (strings[0].equalsIgnoreCase("cps") && strings[1].equalsIgnoreCase("on")) {
            CPSCMD = true;
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("CPS counter turned on"));
        }
        if (strings[0].equalsIgnoreCase("maxcps") && strings[1].matches("[0-9]+")) {

            if (Integer.parseInt(strings[1]) <= 20) {

                if (MINSPEEDSET) {

                    if (Integer.parseInt(strings[1]) > MINSpeed) {
                        MAXSPEEDSET = true;
                        MAXSpeed = Integer.parseInt(strings[1]);
                        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Maximum speed set to " + Integer.parseInt(strings[1])));
                    } else {
                        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Maximum speed needs to be bigger than Minimum speed"));
                    }
                } else {
                    MAXSPEEDSET = true;
                    MAXSpeed = Integer.parseInt(strings[1]);
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Maximum speed set to " + Integer.parseInt(strings[1])));
                }
            } else {

                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("mod maxcps (1-20)"));
            }
        }
        if (strings[0].equalsIgnoreCase("mincps") && strings[1].matches("[0-9]+")) {

            if (Integer.parseInt(strings[1]) <= 20) {

                if (MAXSPEEDSET) {

                    if (Integer.parseInt(strings[1]) < MAXSpeed) {
                        MINSPEEDSET = true;
                        MINSpeed = Integer.parseInt(strings[1]);
                        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Minimum speed set to " + Integer.parseInt(strings[1])));
                    } else {
                        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Minimum speed needs to be smaller than Maximum speed"));
                    }
                } else {
                    MINSPEEDSET = true;
                    MINSpeed = Integer.parseInt(strings[1]);
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Minimum speed set to " + Integer.parseInt(strings[1])));
                }
            } else {

                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("mod mincps (1-20)"));
            }
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender iCommandSender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender iCommandSender, String[] strings, BlockPos blockPos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] strings, int i) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }

    public static void GenerateSequence(int MAX, int MIN, int T) {

        Minecraft mc = Minecraft.getMinecraft();

        if (MAXSPEEDSET && MINSPEEDSET && ClickerON && mc.thePlayer != null && mc.theWorld != null) {

            ClickCount = 0;
            ListGenerated = false;
            ClickList.clear();

            for (int i = 0; i < T; i++) {

                int percentage = r.nextInt(100);

                if (FirstList) {
                    if ((percentage > ((20 - (MIN + MAX) / 2)) / 0.20) && (ClickCount <= MAXSpeed)) {

                        ClickList.add(1);
                        ClickCount++;

                    } else {

                        ClickList.add(0);

                    }
                } else {
                    if ((percentage > ((20 - (TEMP))) / 0.20) && (ClickCount <= MAXSpeed)) {

                        ClickList.add(1);
                        ClickCount++;

                    } else {

                        ClickList.add(0);
                    }
                }
            }

            if (!FirstList) {
                if ((TEMP == ClickCount || TEMP == (ClickCount + 1) || TEMP == (ClickCount - 1)) && (MIN <= ClickCount && ClickCount <= MAX)) {

                    ListGenerated = true;
                    TEMP = ClickCount;

                    //System.out.println(ClickList);
                    //System.out.println("CLICKCOUNT = " + ClickCount);
                    //System.out.println("TEMP = " + TEMP);
                    //System.out.println("Good List");


                    if (CPS && CPSCMD) {
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("CPS : " + ClickCount));
                    }

                } else {

                    System.out.println("List Failed");
                    GenerateSequence(MAXSpeed, MINSpeed, 20);

                }

            } else {

                if (MIN <= ClickCount && ClickCount <= MAX) {

                    FirstList = false;
                    ListGenerated = true;
                    TEMP = ClickCount;
                    System.out.println(ClickList);
                    System.out.println("TEMP =" + TEMP);
                    System.out.println("Good First List");

                } else {

                    GenerateSequence(MAXSpeed, MINSpeed, 20);

                }
            }
        }
    }
}
