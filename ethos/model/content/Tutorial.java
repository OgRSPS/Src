package ethos.model.content;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;

import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.util.Misc;

public class Tutorial {

	/**
	 * The stage of the tutorial
	 */
	private Stage stage = null;

	/**
	 * The final stage of the tutorial
	 */
	private static final Stage FINAL_STAGE = Stage.END;

	/**te
	 * The player in this tutorial
	 */
	private Player player;

	/**
	 * The interface for iron man selection
	 */
	private final IronManInterface ironManInterface;

	/**
	 * h Creates a new tutorial for the player
	 * 
	 * @param player the player the tutorial is created for
	 */
	public Tutorial(Player player) {
		this.player = player;
		this.ironManInterface = new IronManInterface(player);
	}

	/**
	 * A method used to continue the tutorial to the next stage
	 */
	public void proceed() {
		if (stage == Stage.END) {
			return;
		}
		stage = stage.next();
		if (stage == Tutorial.FINAL_STAGE) {
			stop();
			return;
		}
		refresh();
	}

	/**
	 * Refreshes the current stage so that it shows properly for the player. In some cases a player may accidently close an interface, in which case that interface should be
	 * re-opened.
	 */
	public void refresh() {
		if (stage == null) {
			return;
		}
		if (stage == Stage.END) {
			return;
		}
		stage.getScene().display(player);
	}

	/**
	 * Stops the tutorial for the player and finalizes it in some fashion.
	 */
	private void stop() {

	}

	/**
	 * Automatically finishes the tutorial in some fashion without completing it properly.
	 */
	public void autoComplete() {
		stage = Stage.END;
	}

	/**
	 * Determines if the tutorial is still active
	 * 
	 * @return true if the event is active, otherwise false
	 */
	public boolean isActive() {
		return stage != Stage.END;
	}

	/**
	 * The stage for the tutorial.
	 * 
	 * @return the stage the player is on
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * Modifies the stage
	 * 
	 * @param stage the new stage
	 * @return the stage
	 */
	public Stage setStage(Stage stage) {
		return this.stage = stage;
	}

	public IronManInterface getIronmanInterface() {
		return ironManInterface;
	}

	/**
	 * An interface used to display information to the player about a particular scene. Each stage has a scene that displays information to the player.
	 */
	private interface Scene {

		/**
		 * Displays some information about the stage to the player
		 * 
		 * @param player the player
		 */
		void display(Player player);
	}

	/**
	 * The stages of the tutorial
	 */
	public enum Stage {
		START(0, new Scene() {

			@Override
			public void display(Player player) {
				//player.getDH().sendDialogues(645, 311);
				player.getDH().sendDialogues(673, 311);
				
			}

		}), MODE_SELECTION(1, new Scene() {

			@Override
			public void display(Player player) {
				//player.getPA().showInterface(42400);
				player.getPA().sendFrame126("Standard playing style. Enjoy playing Anguish with", 33815);
				player.getPA().sendFrame126("it's default experience rates!", 33816);
				player.getPA().sendFrame126("Perfect for learners!", 33817);
				
				player.getPA().itemOnInterface(3847, 1, 33818, 0);
				player.getPA().itemOnInterface(995, 250000, 33818, 1);
				player.getPA().itemOnInterface(841, 1, 33818, 2);
				player.getPA().itemOnInterface(884, 300, 33818, 3);
				player.getPA().itemOnInterface(1323, 1, 33818, 4);
				player.getPA().itemOnInterface(1067, 1, 33818, 5);
				player.getPA().itemOnInterface(1115, 1, 33818, 6);
				
				player.getPA().itemOnInterface(1153, 1, 33819, 0);
				player.getPA().itemOnInterface(1191, 1, 33819, 1);
				player.getPA().itemOnInterface(1381, 1, 33819, 2);
				player.getPA().itemOnInterface(554, 1000, 33819, 3);
				player.getPA().itemOnInterface(558, 500, 33819, 4);
				player.getPA().itemOnInterface(562, 100, 33819, 5);
				player.getPA().itemOnInterface(3105, 1, 33819, 6);
				
				player.getPA().itemOnInterface(1731, 1, 33820, 0);
				player.getPA().itemOnInterface(7458, 1, 33820, 1);
				player.getPA().itemOnInterface(4413, 1, 33820, 2);
				player.getPA().itemOnInterface(386, 50, 33820, 3);
				player.getPA().itemOnInterface(1351, 1, 33820, 4);
				player.getPA().itemOnInterface(590, 1, 33820, 5);
				player.getPA().itemOnInterface(1265, 1, 33820, 6);
				
				player.getPA().itemOnInterface(4155, 1, 33821, 0);
				player.getPA().itemOnInterface(2433, 5, 33821, 1);
				player.getPA().itemOnInterface(2429, 5, 33821, 2);
				player.getPA().itemOnInterface(114, 5, 33821, 3);
				player.getPA().itemOnInterface(3041, 5, 33821, 4);
				player.getPA().itemOnInterface(2445, 5, 33821, 5);
				player.getPA().itemOnInterface(2435, 5, 33821, 6);
				player.gamemode = 0;
				player.getPA().showInterface(33800);
				//player.getTutorial().getIronmanInterface().refresh();
				
				
			}
		}), MODE_DIALOGUE(2, new Scene() {

			@Override
			public void display(Player player) {
				player.getDH().sendDialogues(647, 311);
			}
		}), APPEND_STARTER(3, new Scene() {

			@Override
			public void display(Player player) {
				if (player.getMode().isIronman()) {
					player.getItems().wearItem(12810, 1, player.playerHat);
					player.getItems().wearItem(12811, 1, player.playerChest);
					player.getItems().wearItem(12812, 1, player.playerLegs);
					player.getItems().wearItem(1323, 1, player.playerWeapon);
					player.getItems().addItem(3847, 1);
					player.getItems().addItem(995, 250000);
					player.getItems().addItem(841, 1);
					player.getItems().addItem(884, 300);
					player.getItems().addItem(1323, 1);
					player.getItems().addItem(1067, 1);
					player.getItems().addItem(1115, 1);
					player.getItems().addItem(1153, 1);
					player.getItems().addItem(1191, 1);
					player.getItems().addItem(1381, 1);
					player.getItems().addItem(554, 1000);
					player.getItems().addItem(558, 500);
					player.getItems().addItem(562, 150);
					player.getItems().addItem(3105, 1);
					player.getItems().addItem(1731, 1);
					player.getItems().addItem(7458, 1);
					player.getItems().addItem(4413, 1);
					player.getItems().addItem(386, 50);
					player.sendMessage("@blu@For additional information about the game, reference the manual in your inventory!");
					player.sendMessage("@blu@It has tons of useful information.");
					PlayerHandler.executeGlobalMessage("[@blu@New Player@bla@] @cr12@" + Misc.capitalizeJustFirst(player.playerName) + " @bla@has logged in for the first time! Welcome!");
				} else if (player.getMode().isUltimateIronman()) {
					player.getItems().wearItem(12813, 1, player.playerHat);
					player.getItems().wearItem(12814, 1, player.playerChest);
					player.getItems().wearItem(12815, 1, player.playerLegs);
					player.getItems().wearItem(1323, 1, player.playerWeapon);
					player.getItems().addItem(3847, 1);
					player.getItems().addItem(995, 250000);
					player.getItems().addItem(1171, 1);
					player.getItems().addItem(841, 1);
					player.getItems().addItem(882, 100);
					player.getItems().addItem(556, 100);
					player.getItems().addItem(558, 100);
					player.getItems().addItem(555, 100);
					player.getItems().addItem(557, 100);
					player.getItems().addItem(559, 100);
					player.getItems().addItem(554, 100);
					player.getItems().addItem(563, 100);
					player.getItems().addItem(1381, 1);
					player.sendMessage("@blu@For additional information about the game, reference the manual in your inventory!");
					player.sendMessage("@blu@It has tons of useful information.");
					PlayerHandler.executeGlobalMessage("[@blu@New Player@bla@] @cr13@" + Misc.capitalizeJustFirst(player.playerName) + " @bla@has logged in for the first time! Welcome!");
				} else {
				//	PlayerHandler.executeGlobalMessage("[@blu@New Player@bla@] " + Misc.capitalizeJustFirst(player.playerName) + " @bla@has logged in for the first time! Welcome!");
					player.getPA().addStarter();
				}
				player.getTutorial().proceed();
			}

		}), END_DIALOGUE(4, new Scene() {

			@Override
			public void display(Player player) {
				player.getDH().sendDialogues(649, 311);
			}

		}), END(5, new Scene() {

			@Override
			public void display(Player player) {
				player.getPA().showInterface(3559);
				player.canChangeAppearance = true;
			}

		});

		/**
		 * The identification value of the stage, representing where this {@link Stage} object exists amongst others in chronological order. The order should initially start at 0
		 * and increase by 1 for every element.
		 */
		private final int id;

		/**
		 * The scene for the stage
		 */
		private final Scene scene;

		/**
		 * Creates a new Stage with an identification value
		 * 
		 * @param id the id of the stage
		 */
		private Stage(int id, Scene scene) {
			this.id = id;
			this.scene = scene;
		}

		/**
		 * Used to compare the {@link Stage#id} of one {@link Stage} to another.
		 */
		private static final Comparator<Stage> COMPARE_STAGES = (one, two) -> Integer.compare(one.id, two.id);

		/**
		 * An unmodifiable {@link Set} of all elements in the {@link Stage} enumeration.
		 */
		private static final List<Stage> ALL_STAGES = ImmutableList.copyOf(Ordering.from(COMPARE_STAGES).sortedCopy(Arrays.asList(values())));

		/**
		 * Retrieves the next element in an ordered list based on where this element exists in that ordered list. The list is ordered by the {@link Stage#id}, in chronological
		 * order.
		 * 
		 * @return
		 */
		public final Stage next() {
			int currentIndex = ALL_STAGES.indexOf(this);

			if (currentIndex == ALL_STAGES.size() - 1) {
				return Stage.END;
			}

			return ALL_STAGES.get(currentIndex + 1);
		}

		/**
		 * The {@link Scene} used to display information about the tutorial to the player
		 * 
		 * @return the scene
		 */
		public final Scene getScene() {
			return scene;
		}
	}
}
