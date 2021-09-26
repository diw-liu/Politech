import HomeScreen from "./components/HomeScreen";
import DataProvider from "./contexts/DataContext";

function App() {
  return (
    <DataProvider>
      <div className="App">
        <HomeScreen />
      </div>
    </DataProvider>
  );
}

export default App;
