package at.ac.hcw.maxflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class FordFulkerson<V> {
    public MaxFlowResult<V> computeMaxFlow(FlowNetwork<V> network, V source, V sink) {
        if (network == null || source == null || sink == null) {
            throw new IllegalArgumentException("Netzwerk, Quelle und Senke werden benoetigt");
        }
        if (Objects.equals(source, sink)) {
            throw new IllegalArgumentException("Quelle und Senke muessen verschieden sein");
        }
        if (!network.vertices().contains(source) || !network.vertices().contains(sink)) {
            throw new IllegalArgumentException("Quelle oder Senke fehlt im Netzwerk");
        }

        network.resetFlows();
        int maxFlow = 0;
        List<String> paths = new ArrayList<>();

        while (true) {
            Map<V, Edge<V>> parentByVertex = new HashMap<>();
            Set<V> visited = new HashSet<>();

            if (!depthFirstSearch(network, source, sink, visited, parentByVertex)) {
                break;
            }

            int bottleneck = determineBottleneck(source, sink, parentByVertex);
            paths.add(formatPath(source, sink, parentByVertex, bottleneck));
            augment(source, sink, parentByVertex, bottleneck);
            maxFlow += bottleneck;
        }

        return new MaxFlowResult<>(maxFlow, paths);
    }

    private boolean depthFirstSearch(
            FlowNetwork<V> network,
            V current,
            V sink,
            Set<V> visited,
            Map<V, Edge<V>> parentByVertex
    ) {
        if (Objects.equals(current, sink)) {
            return true;
        }

        visited.add(current);

        for (Edge<V> edge : network.outgoingEdgesOf(current)) {
            V next = edge.to();
            if (edge.remainingCapacity() <= 0 || visited.contains(next)) {
                continue;
            }

            parentByVertex.put(next, edge);
            if (depthFirstSearch(network, next, sink, visited, parentByVertex)) {
                return true;
            }
        }

        return false;
    }

    private int determineBottleneck(V source, V sink, Map<V, Edge<V>> parentByVertex) {
        int bottleneck = Integer.MAX_VALUE;
        V current = sink;

        while (!Objects.equals(current, source)) {
            Edge<V> edge = parentByVertex.get(current);
            if (edge == null) {
                throw new IllegalStateException("Augmentierender Pfad ist unvollstaendig");
            }
            bottleneck = Math.min(bottleneck, edge.remainingCapacity());
            current = edge.from();
        }

        return bottleneck;
    }

    private void augment(V source, V sink, Map<V, Edge<V>> parentByVertex, int bottleneck) {
        V current = sink;
        while (!Objects.equals(current, source)) {
            Edge<V> edge = parentByVertex.get(current);
            edge.augment(bottleneck);
            current = edge.from();
        }
    }

    private String formatPath(V source, V sink, Map<V, Edge<V>> parentByVertex, int bottleneck) {
        LinkedList<V> vertices = new LinkedList<>();
        V current = sink;
        vertices.addFirst(current);

        while (!Objects.equals(current, source)) {
            Edge<V> edge = parentByVertex.get(current);
            current = edge.from();
            vertices.addFirst(current);
        }

        return String.join(" -> ", vertices.stream().map(String::valueOf).toList())
                + " (Bottleneck: " + bottleneck + ")";
    }
}

