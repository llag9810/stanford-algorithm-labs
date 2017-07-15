#include <bits/stdc++.h>
using namespace std;

class UnionFind
{
public:
    int componentNum;
    vector<int> parent, rank;
    void MakeSet(int n);
    int Find(int x);
    void Union(int x, int y);
};

void UnionFind::MakeSet(int n)
{
    componentNum = n;
    parent.assign(n+1, 0);
    for(int i=1; i<=n; i++)
        parent[i] = i;
    rank.assign(n+1, 0);
}

int UnionFind::Find(int x)
{
    if(x != parent[x])
        parent[x] = Find(parent[x]);
    return parent[x];
}

void UnionFind::Union(int x, int y)
{
    int rootX = Find(x), rootY = Find(y);
    if(rootX != rootY)
    {
        if(rank[rootX] < rank[rootY])
            parent[rootX] = rootY;
        else if(rank[rootY] < rank[rootX])
            parent[rootY] = rootX;
        else
        {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        componentNum--;
    }
}

class Graph:public UnionFind
{
private:
    //For i = 1 and larger, adjList[i][0] = i.
    //For j = 1 and larger, adjList[i][j] is adjacent to i.
    vector<vector<int> > adjList;
    int n;
    vector<pair<int, int> > edgeList;
private:
    int contract();
    void makeEdgeList();
public:
    int minCut(int nIter);
    int getNodeNum();
    friend void buildGraph(char *fname, Graph& g);
};

void Graph::makeEdgeList()
{
    for(int i=1; i<=n; i++)
        for(int j=1; j<adjList[i].size(); j++)
        {
            int node = adjList[i][j];
            if(i < node)
            {
                pair<int, int> edge(i, node);
                edgeList.push_back(edge);
            }
        }
}

int Graph::getNodeNum()
{
    return n;
}

int Graph::contract()
{
    random_shuffle(edgeList.begin(), edgeList.end());
    int i;
    for(i = 0; componentNum > 2; i++)
    {
        int v1 = edgeList[i].first, v2 = edgeList[i].second;
        Union(v1, v2);
    }
    int cut = 0;
    for(; i<edgeList.size(); i++)
    {
        int v1 = edgeList[i].first, v2 = edgeList[i].second;
        int root1 = Find(v1), root2 = Find(v2);
        if(root1 != root2)
            cut++;
    }
    return cut;
}

int Graph::minCut(int nIter)
{
    makeEdgeList();
    srand(time(NULL));
    int mincut = n*n;
    for(int i=0; i<nIter; i++)
    {
        MakeSet(n);
        mincut = min(mincut, contract());
    }
    return mincut;
}

void parse(char str[], vector<vector<int> > &adjList)
{
    int len = strlen(str);
    int offset=0;
    int node;
    sscanf(str,"%d%n",&node,&offset);
    vector<int> lst(1, node);
    while(offset < len)
    {
        int neibor, count;
        sscanf(str+offset, "%d%n", &neibor, &count);
        offset += count;
        lst.push_back(neibor);
        if(offset == len-1)
            break;
    }
    adjList.push_back(lst);
}
void buildGraph(char *fname, Graph &g)
{
    freopen(fname, "r", stdin);
    vector<int> tmp(1, 0);
    g.adjList.push_back(tmp);
    char str[200];
    while(gets(str)!=NULL)
        parse(str, g.adjList);
    g.n = g.adjList.size()-1;
}

int main()
{
    time_t st = clock();
    Graph g;
    buildGraph("MinCut.txt", g);
    int n = g.getNodeNum();
    int nIter = n*n*int(log(1.0*n));
    int mincut = g.minCut(nIter);
    time_t ed = clock();
    printf("%f\n",(double)(ed-st)/CLOCKS_PER_SEC);
    printf("%d\n",mincut);
    return 0;
}